package com.mailytica.ai.api.jira
import spray.json._

case class JiraIssues (issues : List[Issue])

object JiraIssues{

  def parseIssueObjectFromIssueElement(issueJsonString : JsValue): Issue = {

    val JsString(expandString) = issueJsonString.asJsObject.fields("expand")
    val expandSeq = expandString.split(",").toSeq
    val JsString(id) = issueJsonString.asJsObject.fields("id")
    val JsString(key) = issueJsonString.asJsObject.fields("key")
    val JsString(self) = issueJsonString.asJsObject.fields("self")
    val JsString(projectId) = issueJsonString.asJsObject.fields("fields").asJsObject.fields("project").asJsObject.fields("id")
    val JsString(projectKey) = issueJsonString.asJsObject.fields("fields").asJsObject.fields("project").asJsObject.fields("key")

    Issue(Id(id),
      Key(key),
      Self(self),
      ProjectId(projectId),
      ProjectKey(projectKey),
      Expand(expandSeq)
    )
  }
  def parseJson(jsonString : String): JiraIssues = {

    val jsonValue = jsonString.parseJson
    val JsArray(issuesArray) = jsonValue.asJsObject.fields("issues")
    val issuesList = issuesArray.map(parseIssueObjectFromIssueElement).toList

    JiraIssues(issuesList)
  }
}


