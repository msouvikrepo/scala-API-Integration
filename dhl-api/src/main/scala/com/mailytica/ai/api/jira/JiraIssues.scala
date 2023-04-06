package com.mailytica.ai.api.jira

case class JiraIssues (issues : List[Issue])

object JiraIssues{

  def parseJson(jsonString : String): JiraIssues = {
    ???
  }
}


