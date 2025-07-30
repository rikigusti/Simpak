package com.example.simpak

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simpak.adapter.ChatAdapter
import com.example.simpak.model.Chat
import com.google.android.material.bottomnavigation.BottomNavigationView

class ChatActivity : AppCompatActivity() {

    private lateinit var chatAdapter: ChatAdapter
    private val messages = mutableListOf<Chat>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_bantuan)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewChat)
        val etMessage = findViewById<EditText>(R.id.etMessage)
        val btnSend = findViewById<ImageButton>(R.id.btnSend)

        chatAdapter = ChatAdapter(messages)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = chatAdapter

        // Tambahkan pesan awal dari bot
        messages.add(Chat("Halo! Ada yang bisa saya bantu?", isFromBot = true))
        chatAdapter.notifyItemInserted(messages.size - 1)

        btnSend.setOnClickListener {
            val userMessage = etMessage.text.toString().trim()
            if (userMessage.isNotEmpty()) {
                // Tambahkan pesan dari user
                messages.add(Chat(userMessage, isFromBot = false))
                chatAdapter.notifyItemInserted(messages.size - 1)

                // Scroll ke bawah
                recyclerView.scrollToPosition(messages.size - 1)

                // Tambahkan balasan bot (dummy)
                messages.add(Chat("Terima kasih, kami akan bantu sebaik mungkin.", isFromBot = true))
                chatAdapter.notifyItemInserted(messages.size - 1)

                recyclerView.scrollToPosition(messages.size - 1)
                etMessage.text.clear()
            }
        }
        // Bottom Navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_message

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home ->{
                    startActivity(Intent(this, DashboardActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }

                R.id.nav_proposal -> {
                    startActivity(Intent(this, ProposalActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }

                R.id.nav_search -> {
                    startActivity(Intent(this, PilihKelasActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }

                R.id.nav_message ->
                    true
                R.id.nav_account -> {
                    startActivity(Intent(this, AkunActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }

                else -> false
            }
        }
    }
}
