package com.mailytica.ai.api.jira

case class JiraIssues (issues : List[Issue])

object JiraIssues{

  def parseJson(jsonString : String): JiraIssues = {
    ???


    val issue : Issue = Issue(
      Id(""),
      Key(""),
      Self(""),
      ProjectId(""),
      ProjectKey("")
    )
    val issuesList = List(Issue(
      Id(""),
      Key(""),
      Self(""),
      ProjectId(""),
      ProjectKey("")
    ))

    JiraIssues(issuesList)
  }
}


