package com.example.githubuser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.githubuser.datasource.remote.response.MainResponse
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val users = Gson().fromJson(
            application.assets.open("githubuser.json").bufferedReader(),
            MainResponse::class.java
        )
    }
}