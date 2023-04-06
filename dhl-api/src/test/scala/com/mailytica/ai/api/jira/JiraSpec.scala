package com.mailytica.ai.api.jira

import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner
import spray.json.{JsString, JsValue, JsonParser, jsonReader}

@RunWith(classOf[JUnitRunner])
class JiraSpec extends FlatSpec with Matchers with OptionValues {

  it should "convert a json of a jira response with an issue array without elements and return None" in{

  }
  it should "convert a json of a jira response with an issue array with one element" in {

  }
  it should "convert a json of a jira response with an issue array with two elements" in {

  }

}

object JiraSpec{

  def defaultJsonFixture(
                        jiraIssues: JiraIssues = JiraIssues(
                          List(Issue(
                          Id(""),
                          Key(""),
                          Self(""),
                          ProjectId(""),
                          ProjectKey("")
                        ))
                        )): (JsValue, JiraIssues) = {

    ???

  }
}
