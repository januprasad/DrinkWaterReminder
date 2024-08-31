package com.github.januprasad.sample2.screens

import androidx.compose.runtime.Composable
import com.github.januprasad.sample2.repo.UserStore

@Composable
fun UsersDetail() {
    UserRow(user = UserStore.getUser())
}
