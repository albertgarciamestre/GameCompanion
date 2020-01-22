package com.game.gamecompanion.model

import com.google.gson.annotations.SerializedName
import retrofit2.http.Url

data class StreamModel(
    val id: String? = null,
    @SerializedName("user_id") val userId: String? = null,
    @SerializedName("user_name") val username: String? = null,
    @SerializedName("viewer_count") val viewerCount: Int? = null,
    @SerializedName("game_id") val gameId: Int? = null,

    val title: String? = null,
    private val thumbnail_url: String?
) {
    val imageUrl: String?
        get() {
            return thumbnail_url?.replace("{width}x{height}", "768x432")
        }
}


data class TWStreamsResponse(
    val data: ArrayList<StreamModel>? = null
)


