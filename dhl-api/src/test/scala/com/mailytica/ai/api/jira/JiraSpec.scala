package com.mailytica.ai.api.jira

import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner
import spray.json.{JsString, JsValue, JsonParser, jsonReader}

@RunWith(classOf[JUnitRunner])
class JiraSpec extends FlatSpec with Matchers with OptionValues {

  it should "convert a json of a jira response with an issue array without elements and return None" in {

    val issuesListWithoutElements: JiraIssues = JiraIssues(List.empty)
    val(jsonInput, _) =JiraSpec.defaultJsonFixture(issuesListWithoutElements)
    val actualJiraIssues = JiraIssues.parseJson(jsonInput.toString())

    actualJiraIssues shouldEqual issuesListWithoutElements

  }
  it should "convert a json of a jira response with an issue array with one element" in {

    val (jsonInput, expectedJiraIssues) = JiraSpec.defaultJsonFixture()
    val actualJiraIssues = JiraIssues.parseJson(jsonInput.toString())
    expectedJiraIssues shouldEqual actualJiraIssues

  }
  it should "convert a json of a jira response with an issue array with two elements" in {

    val issuesListWithTwoElements: JiraIssues = JiraIssues(
      List(
        Issue(
          Id("1234"),
          Key("2"),
          Self("https://anna-dev.atlassian.net/rest/api/latest/issue/10012"),
          ProjectId("10000"),
          ProjectKey("ML")
        ),
        Issue(
          Id("1235"),
          Key("5"),
          Self("https://anna-dev.atlassian.net/rest/api/latest/issue/10012"),
          ProjectId("10000"),
          ProjectKey("ML")
        )
      )
    )
    val (jsonInput, expectedJiraIssues) = JiraSpec.defaultJsonFixture(issuesListWithTwoElements)
    val actualJiraIssues = JiraIssues.parseJson(jsonInput.toString())
    expectedJiraIssues shouldEqual actualJiraIssues

  }

}

object JiraSpec {

  def defaultJsonFixture(
                          jiraIssues: JiraIssues = JiraIssues(
                            List(
                              Issue(
                                Id("1234"),
                                Key("2"),
                                Self(
                                  "https://anna-dev.atlassian.net/rest/api/latest/issue/10012"
                                ),
                                ProjectId("10000"),
                                ProjectKey("ML")
                              )
                            )
                          )
                        ): (JsValue, JiraIssues) = {


    val jiraJson = s"""{
                     |  "expand": "names,schema",
                     |  "startAt": 0,
                     |  "maxResults": 50,
                     |  "total": 1,
                     |  "issues": [
                     |    {
                     |      "expand": "operations,versionedRepresentations,editmeta,changelog,customfield_10010.requestTypePractice,renderedFields",
                     |      "id": "${jiraIssues.issues.head.id.value}",
                     |      "self": "${jiraIssues.issues.head.self.value}",
                     |      "key": "${jiraIssues.issues.head.key.value}",
                     |      "fields": {
                     |        "customfield_10031": null,
                     |        "project": {
                     |          "self": "https://anna-dev.atlassian.net/rest/api/latest/project/10000",
                     |          "id": "${jiraIssues.issues.head.projectId.value}",
                     |          "key": "${jiraIssues.issues.head.projectKey.value}"
                     |        }
                     |      }
                     |    },
                     |    {
                     |      "expand": "operations,versionedRepresentations,editmeta,changelog,customfield_10010.requestTypePractice,renderedFields",
                     |      "id": "${jiraIssues.issues.tail.head.id.value}",
                     |      "self": "${jiraIssues.issues.tail.head.self.value}",
                     |      "key": "${jiraIssues.issues.tail.head.key.value}",
                     |      "fields": {
                     |        "customfield_10031": null,
                     |        "project": {
                     |          "self": "https://anna-dev.atlassian.net/rest/api/latest/project/10000",
                     |          "id": "${jiraIssues.issues.tail.head.projectId.value}",
                     |          "key": "${jiraIssues.issues.tail.head.projectKey.value}"
                     |        }
                     |      }
                     |    }
                     |  ]
                     |}""".stripMargin


  }
}
