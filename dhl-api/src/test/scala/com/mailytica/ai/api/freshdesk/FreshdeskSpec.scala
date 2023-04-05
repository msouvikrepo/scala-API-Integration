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
                          freshdeskMail: Option[FreshdeskMail] = Some(
                            FreshdeskMail(
                              freshdeskBodyText = FreshdeskBodyText("Hi Lea Artner, Antwort1 auf Testticket"),
                              freshdeskId = FreshdeskId("101002738565".toInt),
                              freshdeskSupportEmail = FreshdeskSupportEmail(FreshdeskEmail("support@newaccount1636718700987.freshdesk.com")),
                              freshdeskToEmails = FreshdeskToEmails([FreshdeskEmail("lea.artner@gmx.de")])
                              )
                          )
                        ): (JsValue, Option[FreshdeskMail]) = {


    // @formatter:on
    (jsonString, dhlStatus)
  }

}
