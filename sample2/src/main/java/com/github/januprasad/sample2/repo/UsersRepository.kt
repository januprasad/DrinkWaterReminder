package com.github.januprasad.sample2.repo

import com.github.januprasad.sample2.model.Response

interface UsersRepository {
    suspend fun getUsers(): Response
}