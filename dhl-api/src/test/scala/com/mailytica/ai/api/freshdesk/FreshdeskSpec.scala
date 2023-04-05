package com.mailytica.ai.api.freshdesk

import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner
import spray.json.{JsValue, JsonParser, jsonReader}

@RunWith(classOf[JUnitRunner])
class FreshdeskSpec extends FlatSpec with Matchers with OptionValues {

  it should "convert a json of a freshdesk response to a freshdesk object" in {
    val (jsonInput, expectedFreshdesk) = FreshdeskSpec.defaultJsonFixture()
    val actualFreshdesk : Freshdesk = Freshdesk.parseJson(jsonInput.toString())
    expectedFreshdesk shouldEqual actualFreshdesk
  }

  it should "convert a json of an empty freshdesk to_email and return a None" in {
  val freshdeskWithoutEmail = Freshdesk(bodyText = BodyText(""),
    id = Id(???),
    supportEmail = SupportEmail(Email("")),
    toEmails = ToEmails(Seq.empty)
  )
    val(jsonInput, _) = FreshdeskSpec.defaultJsonFixture(freshdeskWithoutEmail)
    val actualFreshdesk = Freshdesk.parseJson(jsonInput.toString())

    actualFreshdesk shouldEqual freshdeskWithoutEmail
  }

}

object FreshdeskSpec {

  def defaultJsonFixture(freshdesk: Freshdesk = Freshdesk(
    bodyText = BodyText(""),
    id = Id(???),
    supportEmail = SupportEmail(???),
    toEmails = ToEmails(Seq())
  )
                        ): (JsValue, Freshdesk) = {

    (JsonParser(""), Freshdesk(bodyText = BodyText(""),
      id = Id(???),
      supportEmail = SupportEmail(???),
      toEmails = ToEmails(Seq())))
  }
}