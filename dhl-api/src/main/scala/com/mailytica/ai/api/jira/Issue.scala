package com.mailytica.ai.api.jira

case class Issue(
                  id : Id,
                  key : Key,
                  self : Self,
                  projectId: ProjectId,
                  projectKey: ProjectKey,
                  expand: Expand)