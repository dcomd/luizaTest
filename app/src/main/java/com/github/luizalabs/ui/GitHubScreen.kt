package com.github.luizalabs.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.github.luizalabs.ui.viewMocel.ViewModelGitHubAp


@Composable
fun GitHubScreen(uiState: ViewModelGitHubAp.UiState, viewModelGitHubAp: ViewModelGitHubAp) {


    when (uiState) {
        is ViewModelGitHubAp.UiState.Loading -> {
            Column(
                modifier = Modifier.size(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        is ViewModelGitHubAp.UiState.Error -> {
            Text(text = "Ocorreu um erro ao carregar os dados")
        }
        is ViewModelGitHubAp.UiState.Success -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(uiState.list) { item ->
                    GitHubItemView(
                        userName = item.login,
                        repoName = item.name,
                        starsCount = item.stargazers_count,
                        forksCount = item.forks_count,
                        userImageUrl = item.avatar_url,
                        clickImage = {
                            viewModelGitHubAp.execShow(item.login, item.name)
                        }
                    )
                }
            }
        }

        is ViewModelGitHubAp.UiState.SuccessOwner -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(uiState.list) { item ->
                    GitHubItemView(
                        userName = item.title,
                        repoName = item.user.login,
                        starsCount = 0,
                        forksCount = 0,
                        userImageUrl = item.user.avatar_url,
                        clickImage = {}
                    )
                }
            }
        }
        is ViewModelGitHubAp.UiState.ShowPullRequest -> {
            LaunchedEffect(key1 = Unit) {
                viewModelGitHubAp.fetchOwner(uiState.owner, uiState.repo)
            }
        }
    }
}

@Composable
fun GitHubItemView(
    userName: String,
    repoName: String,
    starsCount: Int,
    forksCount: Int,
    userImageUrl: String,
    modifier: Modifier = Modifier,
    clickImage: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Card(
                modifier = Modifier
                    .size(155.dp, 75.dp)
                    .clickable {
                        clickImage()
                    }
            ) {
                Image(
                    painter = rememberImagePainter(userImageUrl),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }

            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = userName,
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    text = repoName,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }
        }


        Row(
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {

            Row(
                modifier = Modifier
                    .padding(end = 20.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Stars",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "$starsCount",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }


            Row(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "Forks",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "$forksCount",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
    }
}
