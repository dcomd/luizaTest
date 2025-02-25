package com.github.luizalabs.data.repository


import com.github.luizalabs.data.response.GitHubBodyResponse
import com.github.luizalabs.data.response.GitHubPullRequestResponse
import retrofit2.Response

interface GitHubRepository {
    suspend fun execute(): Response<GitHubBodyResponse>

    suspend fun executePullRequest(owner: String, repo: String): Response<List<GitHubPullRequestResponse>>
}