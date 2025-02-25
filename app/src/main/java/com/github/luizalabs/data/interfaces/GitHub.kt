package com.github.luizalabs.data.interfaces

import com.github.luizalabs.data.response.GitHubBodyResponse
import com.github.luizalabs.data.response.GitHubPullRequestResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHub {
    @GET("search/repositories")
    suspend fun search(
        @Query("q") query: String = "kotlin",
        @Query("sort") sort: String = "stars",
        @Query("page") page: Int = 1
    ): retrofit2.Response<GitHubBodyResponse>

    @GET("repos/{owner}/{repo}/pulls")
    suspend fun getPullRequests(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Response<List<GitHubPullRequestResponse>>

}