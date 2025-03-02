package com.example.chatbot

data class ChatData(
    val message: String,
    val role: ChatRole,
)

enum class ChatRole(val role: String) {
    USER("user"),
    MODEL("model")
}
