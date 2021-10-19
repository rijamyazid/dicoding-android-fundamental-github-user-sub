package com.example.githubuser.datasource.remote.api

import com.example.githubuser.datasource.remote.response.SearchResponse
import com.example.githubuser.datasource.remote.response.UserDetailResponse
import com.example.githubuser.datasource.remote.response.UserResponse
import com.example.githubuser.util.NetworkConstant.detailUser
import com.example.githubuser.util.NetworkConstant.listUsers
import com.example.githubuser.util.NetworkConstant.searchUser
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(listUsers)
    @Headers(
        "Authorization: token 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",
        "user-agent: rijamyazid"
    )
    suspend fun getUsers(): List<UserResponse>

    @GET("$detailUser{login}")
    @Headers(
        "Authorization: token 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",
        "user-agent: rijamyazid"
    )
    suspend fun getUserDetail(@Path("login") username: String): UserDetailResponse

    @GET(searchUser)
    @Headers(
        "Authorization: token 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",
        "user-agent: rijamyazid"
    )
    suspend fun getUsersByQuery(@Query("q") query: String): SearchResponse

}