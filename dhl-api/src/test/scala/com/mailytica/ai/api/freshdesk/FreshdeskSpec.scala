package com.mailytica.ai.api.freshdesk

import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner
import spray.json.{JsValue, JsonParser}

@RunWith(classOf[JUnitRunner])
class FreshdeskSpec extends FlatSpec with Matchers with OptionValues {

  it should "convert a json of a freshdeskmail response into FreshDeskMail" in {

    val (jsonInput, expectedFreshdeskMail) = FreshdeskSpec.defaultJsonFixture()

    val actualFreshdeskMail: FreshdeskMail = FreshdeskMail.parseJson(jsonInput.toString()).get

    expectedFreshdeskMail shouldEqual Some(actualFreshdeskMail)
  }

  it should "convert a json of an empty freshdeskmail to_emails response and return a none" in {

    val (jsonInput, _) = FreshdeskSpec.defaultJsonFixture(None)

    val actualFreshdeskMail: Option[FreshdeskMail] = FreshdeskMail.parseJson(jsonInput.toString())

    actualFreshdeskMail shouldEqual None
  }
}

object FreshdeskSpec {

  def defaultJsonFixture(
                          freshdeskMail: FreshdeskMail =  FreshdeskMail(
                              freshdeskBodyText = FreshdeskBodyText("Hi Lea Artner, Antwort1 auf Testticket"),
                              freshdeskId = FreshdeskId("101002738565".toInt),
                              freshdeskSupportEmail = FreshdeskSupportEmail(FreshdeskEmail("support@newaccount1636718700987.freshdesk.com")),
                              freshdeskToEmails = FreshdeskToEmails(Seq(FreshdeskEmail("lea.artner@gmx.de")))
                              )
                        ): (JsValue, FreshdeskMail) = {


    val freshdeskToEmailsJson: String = freshdeskMail.map { freshdeskMail =>

      s"""{
         |  "body_text": "${freshdeskMail.freshdeskBodyText.value}",
         |  "id": ${freshdeskMail.freshdeskId.value},
         |  "incoming": false,
         |  "private": false,
         |  "user_id": 101002862811,
         |  "support_email": "${freshdeskMail.freshdeskSupportEmail.value.value}",
         |  "source": 0,
         |  "category": 1,
         |  "to_emails": ${freshdeskMail.freshdeskToEmails.value.head.value},
         |  "from_email": "Mailytica <support@newaccount1636718700987.freshdesk.com>",
         |  "cc_emails": [
         |
         |  ],
         |  "bcc_emails": [
         |
         |  ],
         |  "email_failure_count": null,
         |  "outgoing_failures": null,
         |  "thread_id": null,
         |  "thread_message_id": null,
         |  "created_at": "2021-11-19T12:20:19Z",
         |  "updated_at": "2021-11-19T12:20:19Z",
         |  "last_edited_at": null,
         |  "last_edited_user_id": null,
         |  "attachments": [
         |
         |  ],
         |  "automation_id": null,
         |  "automation_type_id": null,
         |  "auto_response": false,
         |  "ticket_id": 16,
         |  "source_additional_info": null
         |}""".stripMargin
    }.get

    // @formatter:off
    val jsonString = JsonParser(
      s"""
         """.stripMargin
    )
    // @formatter:on
    (jsonString, freshdeskMail)
  }

}
