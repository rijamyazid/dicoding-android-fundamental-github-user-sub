package com.example.githubuser.datasource.remote.api

import com.example.githubuser.datasource.remote.response.UserDetailResponse
import com.example.githubuser.datasource.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<UserResponse>

    @GET("users/{login}")
    suspend fun getUserDetail(@Path("login") username: String): UserDetailResponse

}