package com.alexandre.apigithub.domain.model

data class GitHubModel(
    var id: Long = 0,
    var name: String = "",
    var full_name: String = "",
    var stargazers_count: Int = 0,
    var forks_count: Int = 0,
    var login: String = "",
    var avatar_url: String = ""
)
