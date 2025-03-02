package com.example.chatbot

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch

class ChatBotVM : ViewModel() {
    val list by lazy {
        mutableStateListOf<ChatData>()
    }
    private val genAI by lazy {
        GenerativeModel(
            modelName = "gemini-1.5-pro",
            apiKey = BuildConfig.GEMINI_API_KEY
        )
    }

    fun sendMessage(message: String) = viewModelScope.launch {
        try {
            val chat = genAI.startChat()

            list.add(ChatData(message, ChatRole.USER))

            chat.sendMessage(
                content(ChatRole.USER.role) { text(message) }
            ).text?.let {
                list.add(ChatData(it, ChatRole.MODEL))
            }
        } catch (e: Exception) {
            Log.e("ChatBotVM", "Error sending message", e)
            // Handle the error, e.g., show an error message to the user
        }
    }
}