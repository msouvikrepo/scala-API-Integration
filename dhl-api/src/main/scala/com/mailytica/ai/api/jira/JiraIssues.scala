package com.mailytica.ai.api.jira
import spray.json._

case class JiraIssues (issues : List[Issue])

object JiraIssues{

  def parseIssueObjectFromIssueElement(issueJsonString : JsValue): Issue = {

    val issueJsObject: JsObject = issueJsonString.asJsObject

    val JsString(expandString) = issueJsObject.fields("expand")
    val expandSeq = expandString.split(",").toSeq

    val JsString(id) = issueJsObject.fields("id")
    val JsString(key) = issueJsObject.fields("key")
    val JsString(self) = issueJsObject.fields("self")

    val projectJsObject: JsObject = issueJsonString.asJsObject.fields("fields").asJsObject.fields("project").asJsObject

    val JsString(projectId) = projectJsObject.asJsObject.fields("id")
    val JsString(projectKey) = projectJsObject.asJsObject.fields("key")

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


