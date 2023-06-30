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
          ProjectKey("ML"),
          Expand(Seq("operations","versionedRepresentations","editmeta","changelog","customfield_10010.requestTypePractice","renderedFields"))
        ),
        Issue(
          Id("1235"),
          Key("5"),
          Self("https://anna-dev.atlassian.net/rest/api/latest/issue/10012"),
          ProjectId("10000"),
          ProjectKey("ML"),
          Expand(Seq("operations","versionedRepresentations","editmeta","changelog","customfield_10010.requestTypePractice","renderedFields"))
        )
      )
    )
    val (jsonInput, expectedJiraIssues) = JiraSpec.defaultJsonFixture(issuesListWithTwoElements)
    val actualJiraIssues = JiraIssues.parseJson(jsonInput.toString())
    expectedJiraIssues shouldEqual actualJiraIssues

  }
}

object JiraSpec {

  def jiraIssue(issue: Issue): String = {

    val issueJson: String = s"""{
                              |      "expand": "${issue.expand.values.mkString(",")}",
                              |      "id": "${issue.id.value}",
                              |      "self": "${issue.self.value}",
                              |      "key": "${issue.key.value}",
                              |      "fields": {
                              |        "customfield_10031": null,
                              |        "project": {
                              |          "self": "https://anna-dev.atlassian.net/rest/api/latest/project/10000",
                              |          "id": "${issue.projectId.value}",
                              |          "key": "${issue.projectKey.value}"
                              |        }
                              |      }
                              |    }""".stripMargin
    issueJson
  }
  def defaultJsonFixture(
                          jiraIssues: JiraIssues = JiraIssues(
                            List(
                              Issue(
                                Id("1234"),
                                Key("2"),
                                Self("https://anna-dev.atlassian.net/rest/api/latest/issue/10012"),
                                ProjectId("10000"),
                                ProjectKey("ML"),
                                Expand(Seq("operations","versionedRepresentations","editmeta","changelog","customfield_10010.requestTypePractice","renderedFields"))
                              )
                            )
                          )
                        ): (JsValue, JiraIssues) = {

    val issuesList:List[String] = jiraIssues.issues.map(jiraIssue)

    val jiraJson = s"""{
                     |  "expand": "names,schema",
                     |  "startAt": 0,
                     |  "maxResults": 50,
                     |  "total": 1,
                     |  "issues": [${issuesList.mkString(",")}]
                     |}""".stripMargin

    (JsonParser(jiraJson), jiraIssues)
  }
}
