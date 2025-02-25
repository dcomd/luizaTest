package com.github.luizalabs.domain

import com.github.luizalabs.data.repository.GitHubRepository
import com.github.luizalabs.data.response.GitHubPullRequestResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GutHubPullRequestListUseCase(private val repository: GitHubRepository) {

    suspend operator fun invoke(owner: String, repo: String): Flow<Result<List<GitHubPullRequestResponse>>> {
        return flow {
            try {
                repository.executePullRequest(owner, repo).body()?.let {
                    emit(Result.success(it))
                }

            } catch (ex: IOException) {
                emit(Result.failure(ex))
            } catch (ex: HttpException) {
                emit(Result.failure(ex))
            }
        }
    }
}