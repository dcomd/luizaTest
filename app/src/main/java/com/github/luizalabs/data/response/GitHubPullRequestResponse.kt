package com.github.luizalabs.data.response


data class GitHubPullRequestResponse(
    val id: Int,
    val title: String,
    val user: User,
    val created_at: String,
    val updated_at: String,
    val state: String,
)

data class User(
    val login: String,
    val avatar_url: String
)
