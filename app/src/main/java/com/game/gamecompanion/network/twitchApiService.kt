package com.game.gamecompanion.network

import com.game.gamecompanion.model.StreamsResponse
import okhttp3.Call
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface TwitchApiService{

    @Headers("Client-ID: ywvglt0gib8rqdly0ejobehqfi071m")
    @GET("streams")
    fun getStreams(): retrofit2.Call<StreamsResponse>

    companion object {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.twitch.tv/helix/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val endpoints = retrofit.create<TwitchApiService>(TwitchApiService::class.java)
    }
}