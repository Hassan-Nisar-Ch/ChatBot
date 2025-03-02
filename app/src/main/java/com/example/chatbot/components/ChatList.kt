package com.example.chatbot.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatbot.ChatData
import com.example.chatbot.ChatRole

@Composable
fun ChatList(
    list: MutableList<ChatData>,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = modifier,
        state = listState,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(list) { chatData ->
            val isUserMessage = chatData.role == ChatRole.USER
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalAlignment = if (isUserMessage) Alignment.End else Alignment.Start
            ) {
                Text(
                    text = chatData.message,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isUserMessage) Color.Black else Color.LightGray)
                        .padding(12.dp),
                    color = if (isUserMessage) Color.White else Color.Black,
                    fontSize = 18.sp,
                    fontWeight = if (isUserMessage) FontWeight.SemiBold else FontWeight.Normal
                )
            }
        }
    }
    LaunchedEffect(list.size) {
        if (list.isNotEmpty()) {
            listState.animateScrollToItem(list.size - 1)
        }
    }
}