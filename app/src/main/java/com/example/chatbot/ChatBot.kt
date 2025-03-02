package com.example.chatbot

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chatbot.components.ChatFooter
import com.example.chatbot.components.ChatList
import com.example.chatbot.ui.theme.ChatBotTheme

@Composable
fun ChatBot(
    viewModel: ChatBotVM = viewModel(),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            if (viewModel.list.isEmpty()) {
                Text(text = "No Chat Available...")
            } else {
                ChatList(list = viewModel.list)
            }
        }

        ChatFooter(onClick = {
            if (it.isNotEmpty()) {
                viewModel.sendMessage(it)
            }
        }
        )

    }
}

@Preview(showSystemUi = true)
@Composable
fun ChatBotPreview() {
    ChatBotTheme {
        ChatBot()
    }
}