package com.github.januprasad.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.januprasad.sample.ui.theme.DrinkwaterReminderTheme
import com.github.januprasad.sample.viewmodel.UiState
import com.github.januprasad.sample.viewmodel.UsersVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DrinkwaterReminderTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(title = {
                            Text(text = "Users", fontSize = 16.sp)
                        })
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        UsersScreen()

                        CoroutineScope(Dispatchers.Main).launch {
                            println("A")
                            launch {
                                delay(4000L)
                                println("B")
                            }
                            println("C")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UsersScreen(viewmodel: UsersVM = viewModel()) {

    LaunchedEffect(true) {
        viewmodel.getUsers()
    }
    val uiState: State<UiState> = viewmodel.usersState.collectAsState()
    when (uiState.value) {
        is UiState.Done -> {
            val users = (uiState.value as UiState.Done).users
            UsersList(users)
        }

        is UiState.Faile -> {}
        UiState.Init -> {}
        UiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun UsersList(users: List<User>) {
    LazyColumn {
        items(users) {
            UserItem(user = it)
        }
    }
}

@Composable
fun UserItem(user: User) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "${user.userId}")
            Text(text = "${user.id}")
            Text(text = "${user.title}", fontSize = 20.sp)
            Text(text = "${user.body}", fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DrinkwaterReminderTheme {
        UsersScreen()
    }
}