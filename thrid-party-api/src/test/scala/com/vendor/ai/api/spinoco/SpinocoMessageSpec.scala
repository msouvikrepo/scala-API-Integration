package com.mailytica.ai.api.spinoco

import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner
import spray.json.{JsValue, JsonParser}

@RunWith(classOf[JUnitRunner])
class SpinocoMessageSpec extends FlatSpec with Matchers with OptionValues {

  it should "convert a json of a Spinoco response to a SpinocoMessage object" in {

    val (jsonInput, expectedSpinocoMessage) = SpinocoMessageSpec.defaultJsonFixture()
    val actualSpinocoMessage: SpinocoMessage = SpinocoMessage.parseJson(jsonInput.toString())
    expectedSpinocoMessage shouldEqual actualSpinocoMessage

  }

  it should "convert the json of a Spinoco response with empty Hashtags" in {

    val spinocoMessageWithNoHashtags: SpinocoMessage = SpinocoMessage(
      subject = Subject("Test"),
      hashtags = Hashtags(Seq.empty),
      fromSourceEmail = FromSourceEmail(Email("tse@spinoco.com")),
      emailMessageBody = EmailMessageBody("Test"),
      destinationToAccount = DestinationToAccount(Email("spinoco@mailytica.com")))

    val (jsonInput, expectedSpinocoMessage) = SpinocoMessageSpec.defaultJsonFixture(spinocoMessageWithNoHashtags)
    val actualSpinocoMessage: SpinocoMessage = SpinocoMessage.parseJson(jsonInput.toString())
    expectedSpinocoMessage shouldEqual actualSpinocoMessage

  }


}

object SpinocoMessageSpec {

  def defaultJsonFixture(
                          spinocoMessage: SpinocoMessage = SpinocoMessage(
                          subject = Subject("Test"),
                          hashtags =  Hashtags(Seq("test1", "test2")),
                          fromSourceEmail = FromSourceEmail(Email("tse@spinoco.com")),
                          emailMessageBody = EmailMessageBody("Test"),
                          destinationToAccount = DestinationToAccount(Email("spinoco@mailytica.com")))
  ):
  (JsValue, SpinocoMessage) = {

    val spinocoMessageJson =s"""{
                              |  "subject": "${spinocoMessage.subject.value}",
                              |  "hashTags": ["${spinocoMessage.hashtags.values.mkString(",").toSeq}"],
                              |  "mailId": "52f7f5f5-f15c-11ec-a548-cd00e3461c72",
                              |  "from": {
                              |    "__tpe": "ContactEndPoint",
                              |    "abId": "64554778-f154-11ec-bf24-b1b0ba2d08da",
                              |    "contactId": "5359c507-f15c-11ec-a548-cd00e3461c72",
                              |    "source": {
                              |      "__tpe": "EmailSource",
                              |      "email": "${spinocoMessage.fromSourceEmail.value.value}"
                              |    }
                              |  },
                              |  "emailMessageBody": {
                              |    "__tpe": "PlainTextBody",
                              |    "body": "${spinocoMessage.emailMessageBody.value}"
                              |  },
                              |  "attachments": [
                              |
                              |  ],
                              |  "destination": {
                              |    "To": [
                              |      {
                              |        "__tpe": "GatewayEndPoint",
                              |        "source": {
                              |          "__tpe": "EmailGatewaySource",
                              |          "gwId": "97cdfd5a-f15b-11ec-a12e-e5cc6cd40e34",
                              |          "account": "${spinocoMessage.destinationToAccount.value.value}"
                              |        }
                              |      }
                              |    ]
                              |  },
                              |  "taskId": "531fc946-f15c-11ec-a548-cd00e3461c72"
                              |}""".stripMargin

    (JsonParser(spinocoMessageJson), spinocoMessage)
  }
}