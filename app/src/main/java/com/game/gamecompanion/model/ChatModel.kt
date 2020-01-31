package com.game.gamecompanion.model

import java.util.Date
import java.sql.Timestamp

data class ChatModel(
    val text: String? = null,
    val timestamp: Long? = null,
    val id: String? = null,
    val name: String? = null,
    var createdAt: String? = null,


    var avatarUrl: String? = null
)