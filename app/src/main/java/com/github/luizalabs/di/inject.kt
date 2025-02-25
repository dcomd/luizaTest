package com.github.luizalabs.di

import com.github.luizalabs.data.RetrofitInstanceGit
import com.github.luizalabs.data.repository.GitHubRepository
import com.github.luizalabs.data.repository.GitHubRepositoryImpl
import com.github.luizalabs.domain.GutHubListUseCase
import com.github.luizalabs.domain.GutHubPullRequestListUseCase
import com.github.luizalabs.ui.viewMocel.ViewModelGitHubAp
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    factory {
        GutHubListUseCase(get())
    }

    factory {
        GutHubPullRequestListUseCase(get())
    }

    factory<GitHubRepository> {
        GitHubRepositoryImpl(get())
    }
    viewModel { ViewModelGitHubAp(get(), get()) }

    single { RetrofitInstanceGit }
}
