package com.example.githubuser.util

import android.util.Log

object NetworkConstant {

    private const val TESTING_TAG = "testingLog"
    const val baseUrl = "https://api.github.com/"

    const val listUsers = "users"
    const val detailUser = "users/"
    const val searchUser = "search/users"

    fun testingLog(message: String) {
        Log.d(TESTING_TAG, message)
    }

}