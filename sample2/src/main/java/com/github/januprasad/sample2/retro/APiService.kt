package com.github.januprasad.sample2.retro

import com.github.januprasad.sample2.model.Response
import retrofit2.http.GET

interface APiService {
    @GET("users")
    suspend fun getUsers(): Response
}