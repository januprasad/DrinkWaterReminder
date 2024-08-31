package com.github.januprasad.sample2.model

data class Response(
    val limit: Int,
    val skip: Int,
    val total: Int,
    val users: List<User>
)