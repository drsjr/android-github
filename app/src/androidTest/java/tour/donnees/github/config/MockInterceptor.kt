package tour.donnees.github.config

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import tour.donnees.github.test.BuildConfig

class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url.toUri().toString()
            val responseString1 = when {
                uri.endsWith("starred") -> getResponse()
                else -> ""
            }

            val responseString = getResponse()

            return chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    ResponseBody.create("application/json".toMediaTypeOrNull(),
                    responseString.toByteArray()))
                .addHeader("content-type", "application/json")
                .build()
        } else {
            throw IllegalAccessError("MockInterceptor is only meant for Testing Purposes and " +
                    "bound to be used only with DEBUG mode")
        }
    }

    private fun getResponse(): String {
        return """
        {
            "total_count": 1,
            "incomplete_results": false,
            "items": [
                {
                    "id": 3432266,
                    "node_id": "MDEwOlJlcG9zaXRvcnkzNDMyMjY2",
                    "name": "kotlin",
                    "full_name": "JetBrains/kotlin",
                    "private": false,
                    "owner": {
                        "login": "JetBrains",
                        "id": 878437,
                        "node_id": "MDEyOk9yZ2FuaXphdGlvbjg3ODQzNw==",
                        "avatar_url": "https://avatars.githubusercontent.com/u/878437?v=4",
                        "gravatar_id": "",
                        "url": "https://api.github.com/users/JetBrains",
                        "html_url": "https://github.com/JetBrains",
                        "followers_url": "https://api.github.com/users/JetBrains/followers",
                        "following_url": "https://api.github.com/users/JetBrains/following{/other_user}",
                        "gists_url": "https://api.github.com/users/JetBrains/gists{/gist_id}",
                        "starred_url": "https://api.github.com/users/JetBrains/starred{/owner}{/repo}",
                        "subscriptions_url": "https://api.github.com/users/JetBrains/subscriptions",
                        "organizations_url": "https://api.github.com/users/JetBrains/orgs",
                        "repos_url": "https://api.github.com/users/JetBrains/repos",
                        "events_url": "https://api.github.com/users/JetBrains/events{/privacy}",
                        "received_events_url": "https://api.github.com/users/JetBrains/received_events",
                        "type": "Organization",
                        "site_admin": false
                    },
                    "html_url": "https://github.com/JetBrains/kotlin",
                    "description": "The Kotlin Programming Language. ",
                    "fork": false,
                    "url": "https://api.github.com/repos/JetBrains/kotlin",
                    "forks_url": "https://api.github.com/repos/JetBrains/kotlin/forks",
                    "keys_url": "https://api.github.com/repos/JetBrains/kotlin/keys{/key_id}",
                    "collaborators_url": "https://api.github.com/repos/JetBrains/kotlin/collaborators{/collaborator}",
                    "teams_url": "https://api.github.com/repos/JetBrains/kotlin/teams",
                    "hooks_url": "https://api.github.com/repos/JetBrains/kotlin/hooks",
                    "issue_events_url": "https://api.github.com/repos/JetBrains/kotlin/issues/events{/number}",
                    "events_url": "https://api.github.com/repos/JetBrains/kotlin/events",
                    "assignees_url": "https://api.github.com/repos/JetBrains/kotlin/assignees{/user}",
                    "branches_url": "https://api.github.com/repos/JetBrains/kotlin/branches{/branch}",
                    "tags_url": "https://api.github.com/repos/JetBrains/kotlin/tags",
                    "blobs_url": "https://api.github.com/repos/JetBrains/kotlin/git/blobs{/sha}",
                    "git_tags_url": "https://api.github.com/repos/JetBrains/kotlin/git/tags{/sha}",
                    "git_refs_url": "https://api.github.com/repos/JetBrains/kotlin/git/refs{/sha}",
                    "trees_url": "https://api.github.com/repos/JetBrains/kotlin/git/trees{/sha}",
                    "statuses_url": "https://api.github.com/repos/JetBrains/kotlin/statuses/{sha}",
                    "languages_url": "https://api.github.com/repos/JetBrains/kotlin/languages",
                    "stargazers_url": "https://api.github.com/repos/JetBrains/kotlin/stargazers",
                    "contributors_url": "https://api.github.com/repos/JetBrains/kotlin/contributors",
                    "subscribers_url": "https://api.github.com/repos/JetBrains/kotlin/subscribers",
                    "subscription_url": "https://api.github.com/repos/JetBrains/kotlin/subscription",
                    "commits_url": "https://api.github.com/repos/JetBrains/kotlin/commits{/sha}",
                    "git_commits_url": "https://api.github.com/repos/JetBrains/kotlin/git/commits{/sha}",
                    "comments_url": "https://api.github.com/repos/JetBrains/kotlin/comments{/number}",
                    "issue_comment_url": "https://api.github.com/repos/JetBrains/kotlin/issues/comments{/number}",
                    "contents_url": "https://api.github.com/repos/JetBrains/kotlin/contents/{+path}",
                    "compare_url": "https://api.github.com/repos/JetBrains/kotlin/compare/{base}...{head}",
                    "merges_url": "https://api.github.com/repos/JetBrains/kotlin/merges",
                    "archive_url": "https://api.github.com/repos/JetBrains/kotlin/{archive_format}{/ref}",
                    "downloads_url": "https://api.github.com/repos/JetBrains/kotlin/downloads",
                    "issues_url": "https://api.github.com/repos/JetBrains/kotlin/issues{/number}",
                    "pulls_url": "https://api.github.com/repos/JetBrains/kotlin/pulls{/number}",
                    "milestones_url": "https://api.github.com/repos/JetBrains/kotlin/milestones{/number}",
                    "notifications_url": "https://api.github.com/repos/JetBrains/kotlin/notifications{?since,all,participating}",
                    "labels_url": "https://api.github.com/repos/JetBrains/kotlin/labels{/name}",
                    "releases_url": "https://api.github.com/repos/JetBrains/kotlin/releases{/id}",
                    "deployments_url": "https://api.github.com/repos/JetBrains/kotlin/deployments",
                    "created_at": "2012-02-13T17:29:58Z",
                    "updated_at": "2023-03-17T14:49:43Z",
                    "pushed_at": "2023-03-17T15:31:35Z",
                    "git_url": "git://github.com/JetBrains/kotlin.git",
                    "ssh_url": "git@github.com:JetBrains/kotlin.git",
                    "clone_url": "https://github.com/JetBrains/kotlin.git",
                    "svn_url": "https://github.com/JetBrains/kotlin",
                    "homepage": "https://kotlinlang.org",
                    "size": 1811534,
                    "stargazers_count": 44162,
                    "watchers_count": 44162,
                    "language": "Kotlin",
                    "has_issues": false,
                    "has_projects": false,
                    "has_downloads": true,
                    "has_wiki": false,
                    "has_pages": false,
                    "has_discussions": false,
                    "forks_count": 5462,
                    "mirror_url": null,
                    "archived": false,
                    "disabled": false,
                    "open_issues_count": 149,
                    "license": null,
                    "allow_forking": true,
                    "is_template": false,
                    "web_commit_signoff_required": false,
                    "topics": [
                        "compiler",
                        "gradle-plugin",
                        "intellij-plugin",
                        "kotlin",
                        "kotlin-library",
                        "maven-plugin",
                        "programming-language"
                    ],
                    "visibility": "public",
                    "forks": 5462,
                    "open_issues": 149,
                    "watchers": 44162,
                    "default_branch": "master",
                    "score": 1.0
                }
            ]
        }""".trimIndent()
    }

}