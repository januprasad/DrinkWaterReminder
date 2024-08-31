package com.github.januprasad.sample2.repo

import com.github.januprasad.sample2.model.Response
import com.github.januprasad.sample2.retro.APiService
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(private val repo: APiService): UsersRepository
{
    override suspend fun getUsers(): Response {
      return repo.getUsers()
    }
}