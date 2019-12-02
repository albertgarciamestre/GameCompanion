package com.game.gamecompanion.model

import com.google.gson.annotations.SerializedName

data class StreamModel(
    val id: String? = null,
    @SerializedName("user_id") val userId: String? = null,
    @SerializedName("user_name") val username: String? = null,
    @SerializedName("viewer_count") val viewerCount: Int? = null,

    val title: String? = null,
    private val thumbnail_url: String? = null
) {
    val imageUrl: String?
        get() {
            return thumbnail_url?.replace("{width}x{height}", "500x500")
        }
}


data class StreamsResponse(
    val data: ArrayList<StreamModel>? = null,
    val pagination:TwitchPagination? = null
)

data class TwitchPagination(
    val cursor: String? = null
)


