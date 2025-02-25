package com.github.luizalabs.ui.viewMocel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.luizalabs.domain.GutHubListUseCase
import com.alexandre.apigithub.domain.model.GitHubModel
import com.github.luizalabs.data.response.GitHubPullRequestResponse
import com.github.luizalabs.domain.GutHubPullRequestListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ViewModelGitHubAp(private val gitHubListUseCase: GutHubListUseCase, private val gitHubPullRequestListUseCase: GutHubPullRequestListUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    fun fetch() {
        viewModelScope.launch {

            _uiState.value = UiState.Loading
                gitHubListUseCase.invoke().collectLatest { result ->
                    result.fold(
                        onSuccess = { data ->
                            _uiState.value = UiState.Success(data)
                        },
                        onFailure = { error ->
                            _uiState.value = UiState.Error
                        }
                    )
                }
        }

    }

    fun fetchOwner(owner: String , repo: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            gitHubPullRequestListUseCase.invoke(owner, repo).collectLatest { result ->
                result.fold(
                    onSuccess = { data ->
                        _uiState.value = UiState.SuccessOwner(data)
                    },
                    onFailure = { error ->
                        _uiState.value = UiState.Error
                    }
                )
            }
        }

    }

    fun execShow(owner: String, repo: String){
        _uiState.value = UiState.ShowPullRequest(owner, repo)
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val list: List<GitHubModel>) : UiState()
        data class SuccessOwner(val list:  List<GitHubPullRequestResponse>) : UiState()
        object Error : UiState()
        data class ShowPullRequest(val owner: String, val repo: String) : UiState()
    }
}
