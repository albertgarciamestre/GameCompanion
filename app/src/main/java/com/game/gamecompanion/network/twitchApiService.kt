package com.game.gamecompanion.network

import com.game.gamecompanion.model.TWStreamsResponse
import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface TwitchApiService{

    @Headers("Client-ID: ywvglt0gib8rqdly0ejobehqfi071m")
    @GET("streams?first=10&game_id=110758")
    fun getStreams(): Call<TWStreamsResponse>

    companion object {
        var retrofit = Retrofit.Builder()
            .baseUrl("https://api.twitch.tv/helix/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create<TwitchApiService>(TwitchApiService::class.java)
    }
}