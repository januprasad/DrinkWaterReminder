package com.github.januprasad.sample2.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.github.januprasad.sample2.Dimen
import com.github.januprasad.sample2.model.User
import com.github.januprasad.sample2.vm.AppState
import com.github.januprasad.sample2.vm.UsersVM
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Composable
fun UsersScreen(
    viewModel: UsersVM = viewModel(),
    navigateDetailsScreen: (User) -> Unit
) {

    val appState by viewModel.uiState.collectAsState()

    LaunchedEffect(true) {
        viewModel.getUsers()
    }

    when (appState) {
        is AppState.Init -> {}
        is AppState.Loaded -> UsersList((appState as AppState.Loaded).users, navigateDetailsScreen)
        is AppState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is AppState.Error -> Text(text = "Something went wrong")

    }
}

@Composable
fun UsersList(users: List<User>, navigateDetailsScreen: (User) -> Unit) {
    LazyColumn {
        items(users) { user ->
            UserRow(user, navigateDetailsScreen)
        }
    }
}

@Composable
fun UserRow(user: User, navigateDetailsScreen: (User) -> Unit = {}) {
    //left image right content
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimen.BASE_8.times(2)),
        onClick = {
            navigateDetailsScreen(user)
        }) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            Box(contentAlignment = Alignment.Center) {
                CoilImageWrapper(user.image)
            }

            Column(
                modifier = Modifier
                    .padding(Dimen.BASE_8.times(1)),
            ) {
                Text(text = user.firstName)
                Text(text = user.lastName)
                Text(text = user.email)
                Text(text = user.phone)
            }
        }


    }
}

@Composable
fun CoilImageWrapper(image: String) {
    Column {
        AsyncImage(
            modifier = Modifier
                .size(
                    width = Dimen.BASE_8.times(16),
                    height = Dimen.BASE_8.times(18)
                ),
            model = image,
            contentDescription = null,
        )
    }
}

