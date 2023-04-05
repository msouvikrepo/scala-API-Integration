package com.mailytica.ai.api.freshdesk

import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner
import spray.json.{JsValue, JsonParser}

@RunWith(classOf[JUnitRunner])
class FreshdeskSpec extends FlatSpec with Matchers with OptionValues {

  it should "convert a json of a freshdesk response to a freshdesk object" in {


  }

  it should "convert a json of an empty freshdesk to_email and return a None" in {


  }

}

object FreshdeskSpec {

  def defaultJsonFixture(freshdesk: Freshdesk = Freshdesk(
    bodyText = BodyText(""),
    id = Id(???),
    supportEmail = SupportEmail(???),
    toEmails = ToEmails(Seq())
  )
                        ):(JsValue, Freshdesk) = {

    (JsonParser(""), Freshdesk(bodyText = BodyText(""),
      id = Id(???),
      supportEmail = SupportEmail(???),
      toEmails = ToEmails(Seq())))
  }
}