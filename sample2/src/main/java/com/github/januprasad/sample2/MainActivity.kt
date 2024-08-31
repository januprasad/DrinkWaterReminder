package com.github.januprasad.sample2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.januprasad.sample2.screens.Screens
import com.github.januprasad.sample2.screens.UsersDetail
import com.github.januprasad.sample2.screens.UsersScreen
import com.github.januprasad.sample2.ui.theme.DrinkwaterReminderTheme
import com.github.januprasad.sample2.vm.UsersVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DrinkwaterReminderTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(title = {
                            Text(text = "User List")
                        })
                    }
                ) { innerPadding ->

                    val navController = rememberNavController()
                    val usersVM: UsersVM = hiltViewModel()
                    Column(Modifier.padding(innerPadding)) {
                        NavHost(
                            navController = navController,
                            startDestination = Screens.UsersListScreen.toString()
                        ) {
                            composable(Screens.UsersListScreen.toString()) {
                                UsersScreen(usersVM) {
                                    navController.navigate(Screens.UsersDetailScreen.toString())
                                }
                            }

                            composable(Screens.UsersDetailScreen.toString()) {
                                UsersDetail()
                            }
                        }
                    }

                }
            }
        }
    }

}

object Dimen {
    val BASE_8 = 8.dp
}
