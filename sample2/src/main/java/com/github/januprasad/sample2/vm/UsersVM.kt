package com.github.januprasad.sample2.vm

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.januprasad.sample2.model.User
import com.github.januprasad.sample2.repo.UserStore
import com.github.januprasad.sample2.repo.UsersRepositoryImpl
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersVM @Inject constructor(private val usersRepoImpl: UsersRepositoryImpl) : ViewModel() {

    private var _uiState = MutableStateFlow<AppState>(AppState.Init)
    val uiState = _uiState


    fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update {
                AppState.Loading
            }
            val result = usersRepoImpl.getUsers()
            _uiState.update {
                AppState.Loaded(result.users)
            }
        }
    }

    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            repeat(30) {
//                delay(1000L)
//                counter.emit(it)
//            }
//        }
    }

    fun storeUser(user: User) {
        UserStore.storeUser(user)
    }
}

sealed class AppState {
    object Init : AppState()
    object Loading : AppState()
    data class Loaded(val users: List<User>) : AppState()
    data class Error(val message: String) : AppState()
}

sealed class UserState {
    object None : UserState()
    data class SelectedUser(val user: User) : UserState()
}