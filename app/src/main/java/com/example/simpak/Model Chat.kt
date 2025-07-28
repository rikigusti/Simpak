package com.example.simpak.model

data class Chat(
    val text: String,
    val isFromBot: Boolean = true // default dari bot, bisa diubah saat kirim
)
