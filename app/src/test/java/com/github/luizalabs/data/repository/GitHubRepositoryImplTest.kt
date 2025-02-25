package com.github.luizalabs.data.repository

import com.github.luizalabs.data.RetrofitInstanceGit
import com.github.luizalabs.data.response.GitHubBodyResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class GitHubRepositoryImplTest {

    private lateinit var gitHubRepositoryImpl: GitHubRepositoryImpl
    private val retrofitInstanceGit: RetrofitInstanceGit = mockk()

    private val dispatcher = TestCoroutineDispatcher()
    @Before
    fun setup() {
        gitHubRepositoryImpl = GitHubRepositoryImpl(retrofitInstanceGit)

        Dispatchers.setMain(dispatcher)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test execute should return list of repositories`() = runBlockingTest {

        val fakeGitHubResponse = GitHubBodyResponse(items = listOf())

        coEvery { retrofitInstanceGit.getRetrofit().search() } returns Response.success(fakeGitHubResponse)

        val response = gitHubRepositoryImpl.execute()

        coVerify { retrofitInstanceGit.getRetrofit().search() }

        assert(response.isSuccessful)
        assert(response.body()?.items?.isEmpty() == true)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test executePullRequest should return list of pull requests`() = runBlockingTest {

        coEvery { retrofitInstanceGit.getRetrofit().getPullRequests("owner", "repo") } returns Response.success(
            mockk()
        )

        val response = gitHubRepositoryImpl.executePullRequest("owner", "repo")

        coVerify { retrofitInstanceGit.getRetrofit().getPullRequests("owner", "repo") }

        assert(response.isSuccessful)
        assert(response.body()?.isNotEmpty() == true)
    }
}
