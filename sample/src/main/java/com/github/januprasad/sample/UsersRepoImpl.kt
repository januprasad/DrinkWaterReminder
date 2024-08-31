package com.github.januprasad.sample

class UsersRepoImpl {
    suspend fun getUsers(): List<User> {
        val result = RetrofitService.service.getUsers()
        return result
    }
}