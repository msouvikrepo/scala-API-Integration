package com.mailytica.ai.api.freshdesk

import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner
import spray.json.{JsString, JsValue, JsonParser, jsonReader}

@RunWith(classOf[JUnitRunner])
class FreshdeskSpec extends FlatSpec with Matchers with OptionValues {

  it should "convert a json of a freshdesk response to a freshdesk object" in {
    val (jsonInput, expectedFreshdesk) = FreshdeskSpec.defaultJsonFixture()
    val actualFreshdesk: Freshdesk = Freshdesk.parseJson(jsonInput.toString())
    expectedFreshdesk shouldEqual actualFreshdesk
  }

  it should "convert a json of an empty freshdesk to_email and return a None" in {
    val freshdeskWithoutEmail = Freshdesk(
      bodyText = BodyText("Hi Lea Artner, Antwort1 auf Testticket"),
      id = Id(101002738565L),
      supportEmail = SupportEmail(Email("support@newaccount1636718700987.freshdesk.com")),
      toEmails = ToEmails(Seq.empty)
    )
    val (jsonInput, _) = FreshdeskSpec.defaultJsonFixture(freshdeskWithoutEmail)
    val actualFreshdesk = Freshdesk.parseJson(jsonInput.toString())
    actualFreshdesk shouldEqual freshdeskWithoutEmail
  }

}

object FreshdeskSpec {

  def defaultJsonFixture(
                          freshdesk: Freshdesk = Freshdesk(
                            bodyText = BodyText("Hi Lea Artner, Antwort1 auf Testticket"),
                            id = Id(101002738565L),
                            supportEmail = SupportEmail(Email("support@newaccount1636718700987.freshdesk.com")),
                            toEmails = ToEmails(Seq(Email("lea.artner@gmx.de")))
                          )
                        ): (JsValue, Freshdesk) = {
    val freshdeskJson =
      s"""
        |{
        |  "body_text": ${freshdesk.bodyText.value},
        |  "id": ${freshdesk.id.value},
        |  "incoming": false,
        |  "private": false,
        |  "user_id": 101002862811,
        |  "support_email": ${freshdesk.supportEmail.value.value},
        |  "source": 0,
        |  "category": 1,
        |  "to_emails": [${freshdesk.toEmails.values.map(email => JsString(email.value)).mkString(",")}],
        |  "from_email": "Mailytica <support@newaccount1636718700987.freshdesk.com>",
        |  "cc_emails": [
        |  ],
        |  "bcc_emails": [
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
        |  ],
        |  "automation_id": null,
        |  "automation_type_id": null,
        |  "auto_response": false,
        |  "ticket_id": 16,
        |  "source_additional_info": null
        |}
        |""".stripMargin

    (JsonParser(freshdeskJson), freshdesk)
  }
}