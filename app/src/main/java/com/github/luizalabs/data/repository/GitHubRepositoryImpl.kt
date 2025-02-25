package com.github.luizalabs.data.repository


import com.github.luizalabs.data.RetrofitInstanceGit
import com.github.luizalabs.data.repository.GitHubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GitHubRepositoryImpl(private val api: RetrofitInstanceGit): GitHubRepository {

    override suspend fun execute() = withContext(Dispatchers.IO) {
        return@withContext api.getRetrofit().search()
    }

    override suspend fun executePullRequest(owner:String , repo: String) = withContext(Dispatchers.IO) {
        return@withContext api.getRetrofit().getPullRequests(owner, repo)
    }

}