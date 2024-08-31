package com.github.januprasad.sample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.januprasad.sample.User
import com.github.januprasad.sample.UsersRepoImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UsersVM : ViewModel() {
    val userRepository = UsersRepoImpl()
    private var _usersState = MutableStateFlow<UiState>(UiState.Init)
    val usersState: StateFlow<UiState> = _usersState
    fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            _usersState.update {
                UiState.Loading
            }
            val users = userRepository.getUsers()
            _usersState.value = UiState.Done(users = users.reversed())
        }
    }
}

sealed class UiState() {
    object Init : UiState()
    object Loading : UiState()
    data class Done(val users: List<User>) : UiState()
    data class Faile(val error: String) : UiState()
}