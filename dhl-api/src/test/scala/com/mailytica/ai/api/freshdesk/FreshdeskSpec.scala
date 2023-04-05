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

  it should "convert a json of an empty freshdeskmail response and return a none" in {

    val (jsonInput, _) = FreshdeskSpec.defaultJsonFixture(None)

    val actualFreshdeskMail: Option[FreshdeskMail] = FreshdeskMail.parseJson(jsonInput.toString())

    actualFreshdeskMail shouldEqual None
  }
}

object FreshdeskSpec {

  def defaultJsonFixture(
                          dhlStatus: Option[FreshdeskMail] = Some(
                            FreshdeskMail(
                              freshdeskBodyText = FreshdeskBodyText("Hi Lea Artner, Antwort1 auf Testticket"),
                              freshdeskId = FreshdeskId("101002738565".toInt),
                              freshdeskSupportEmail = FreshdeskSupportEmail(FreshdeskEmail("support@newaccount1636718700987.freshdesk.com")),
                              freshdeskToEmails = FreshdeskToEmails([FreshdeskEmail("lea.artner@gmx.de")])
                              )
                          )
                        ): (JsValue, Option[FreshdeskMail]) = {

    val shipmentJson: Option[String] = dhlStatus.map { dhlStatus =>

      s"""{
         |  "body_text": "Hi Lea Artner, Antwort1 auf Testticket",
         |  "id": 101002738565,
         |  "incoming": false,
         |  "private": false,
         |  "user_id": 101002862811,
         |  "support_email": "support@newaccount1636718700987.freshdesk.com",
         |  "source": 0,
         |  "category": 1,
         |  "to_emails": [
         |    "lea.artner@gmx.de"
         |  ],
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
    }

    // @formatter:off
    val jsonString = JsonParser(
      s"""
         |{
         |  "shipments": [
         |     ${shipmentJson.getOrElse("")}
         |  ],
         |  "possibleAdditionalShipmentsUrl": [
         |    "/track/shipments?trackingNumber=470800204932&language=DE&service=freight",
         |    "/track/shipments?trackingNumber=470800204932&language=DE&service=dgf",
         |    "/track/shipments?trackingNumber=470800204932&language=DE&service=ecommerce",
         |    "/track/shipments?trackingNumber=470800204932&language=DE&service=parcel-nl",
         |    "/track/shipments?trackingNumber=470800204932&language=DE&service=parcel-pl",
         |    "/track/shipments?trackingNumber=470800204932&language=DE&service=express",
         |    "/track/shipments?trackingNumber=470800204932&language=DE&service=post-de",
         |    "/track/shipments?trackingNumber=470800204932&language=DE&service=sameday",
         |    "/track/shipments?trackingNumber=470800204932&language=DE&service=parcel-uk",
         |    "/track/shipments?trackingNumber=470800204932&language=DE&service=ecommerce-apac",
         |    "/track/shipments?trackingNumber=470800204932&language=DE&service=ecommerce-europe",
         |    "/track/shipments?trackingNumber=470800204932&language=DE&service=svb",
         |    "/track/shipments?trackingNumber=470800204932&language=DE&service=post-international"
         |  ]
         |}
         |""".stripMargin
    )
    // @formatter:on
    (jsonString, dhlStatus)
  }

}
