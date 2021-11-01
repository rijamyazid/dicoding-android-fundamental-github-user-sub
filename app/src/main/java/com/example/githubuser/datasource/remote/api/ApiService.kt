package com.example.githubuser.datasource.remote.api

import com.example.githubuser.BuildConfig
import com.example.githubuser.datasource.remote.response.SearchResponse
import com.example.githubuser.datasource.remote.response.UserDetailResponse
import com.example.githubuser.datasource.remote.response.UserResponse
import com.example.githubuser.util.NetworkConstant.DETAIL_USER
import com.example.githubuser.util.NetworkConstant.FOLLOWERS_USER
import com.example.githubuser.util.NetworkConstant.FOLLOWING_USER
import com.example.githubuser.util.NetworkConstant.LIST_USER
import com.example.githubuser.util.NetworkConstant.SEARCH_USER
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(LIST_USER)
    @Headers(
        "Authorization: token ${BuildConfig.GITHUB_TOKEN}",
        "user-agent: ${BuildConfig.GITHUB_USER_AGENT}"
    )
    suspend fun getUsers(): List<UserResponse>

    @GET("$DETAIL_USER{login}")
    @Headers(
        "Authorization: token ${BuildConfig.GITHUB_TOKEN}",
        "user-agent: ${BuildConfig.GITHUB_USER_AGENT}"
    )
    suspend fun getUserDetail(@Path("login") username: String): UserDetailResponse

    @GET(SEARCH_USER)
    @Headers(
        "Authorization: token ${BuildConfig.GITHUB_TOKEN}",
        "user-agent: ${BuildConfig.GITHUB_USER_AGENT}"
    )
    suspend fun getUsersByQuery(@Query("q") query: String): SearchResponse

    @GET("$DETAIL_USER{login}/$FOLLOWERS_USER")
    @Headers(
        "Authorization: token ${BuildConfig.GITHUB_TOKEN}",
        "user-agent: ${BuildConfig.GITHUB_USER_AGENT}"
    )
    suspend fun getUserFollowers(@Path("login") username: String): List<UserResponse>

    @GET("$DETAIL_USER{login}/$FOLLOWING_USER")
    @Headers(
        "Authorization: token ${BuildConfig.GITHUB_TOKEN}",
        "user-agent: ${BuildConfig.GITHUB_USER_AGENT}"
    )
    suspend fun getUserFollowing(@Path("login") username: String): List<UserResponse>

}