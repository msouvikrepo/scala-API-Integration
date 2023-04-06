package com.mailytica.ai.api.jira
import spray.json._

case class JiraIssues (issues : List[Issue])

object JiraIssues{

  def parseJson(jsonString : String): JiraIssues = {

    ???
  }
}


