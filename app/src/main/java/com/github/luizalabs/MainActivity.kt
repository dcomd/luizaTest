package com.github.luizalabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.github.luizalabs.ui.viewMocel.ViewModelGitHubAp
import com.github.luizalabs.ui.GitHubScreen
import com.github.luizalabs.ui.theme.LuizalabsTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val gitHubViewModel: ViewModelGitHubAp by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gitHubViewModel.fetch()
        setContent {
            LuizalabsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val uiState = gitHubViewModel.uiState.collectAsState().value

                    GitHubScreen(uiState = uiState, gitHubViewModel)
                }
            }
        }
    }
}
