package com.github.luizalabs.domain

import com.alexandre.apigithub.domain.model.GitHubModel
import com.github.luizalabs.data.repository.GitHubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GutHubListUseCase(private val repository: GitHubRepository) {

    suspend operator fun invoke(): Flow<Result<List<GitHubModel>>> {
        return flow {
            try {
                emit(
                    Result.success(
                        MapperModel.factory(repository.execute().body()!!)
                    )
                )

            } catch (ex: IOException) {
                emit(Result.failure(ex))
            } catch (ex: HttpException) {
                emit(Result.failure(ex))
            }
        }
    }
}