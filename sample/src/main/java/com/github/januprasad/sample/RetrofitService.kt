package com.github.januprasad.sample

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitService {

    const val BASE = "https://jsonplaceholder.typicode.com/"

    val api = Retrofit
        .Builder()
        .baseUrl(BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    var service: UsersApi = api.create(UsersApi::class.java)

}
