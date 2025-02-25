package com.github.luizalabs.domain

import com.alexandre.apigithub.domain.model.GitHubModel
import com.github.luizalabs.data.response.GitHubItemsResponse


fun GitHubItemsResponse.toGitHubModelDb(): List<GitHubModel> {
    return mutableListOf(
        GitHubModel(
            name = this.name,
            full_name = this.full_name,
            stargazers_count = this.stargazers_count,
            forks_count = this.forks_count,
            login = this.owner.login,
            avatar_url = this.owner.avatar_url
        )
    )
}

