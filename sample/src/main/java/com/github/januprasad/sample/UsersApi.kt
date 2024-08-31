package com.github.januprasad.sample

import retrofit2.http.GET

interface UsersApi {

    @GET("/posts")
    suspend fun getUsers(): List<User>
}