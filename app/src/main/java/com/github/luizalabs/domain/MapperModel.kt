package com.github.luizalabs.domain

import com.alexandre.apigithub.domain.model.GitHubModel
import com.github.luizalabs.data.response.GitHubBodyResponse

object MapperModel {

    fun factory(
        gitHubModel: GitHubBodyResponse
    ): List<GitHubModel>{
        return gitHubModel.items.flatMap { it.toGitHubModelDb() }
    }
}