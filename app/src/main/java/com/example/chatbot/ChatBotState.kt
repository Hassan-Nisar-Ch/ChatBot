package com.example.chatbot

sealed interface ChatBotUiState{
    data object Ideal: ChatBotUiState
    data object Loading: ChatBotUiState
    data class Error(val chatData: String): ChatBotUiState
    data class Success(val chatError: String): ChatBotUiState
}