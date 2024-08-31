package com.github.januprasad.sample2.repo

import com.github.januprasad.sample2.model.User

object UserStore{
    private lateinit var user: User
    fun storeUser(user: User){
        this.user = user
    }
    fun getUser() = this.user
}