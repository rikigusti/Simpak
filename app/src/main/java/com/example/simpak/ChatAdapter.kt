package com.example.simpak.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simpak.R
import com.example.simpak.model.Chat

class ChatAdapter(private val list: List<Chat>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_BOT = 0
        private const val VIEW_TYPE_USER = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position].isFromBot) VIEW_TYPE_BOT else VIEW_TYPE_USER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == VIEW_TYPE_BOT) {
            val view = inflater.inflate(R.layout.item_chat_bot, parent, false)
            BotViewHolder(view)
        } else {
            val view = inflater.inflate(R.layout.item_chat_user, parent, false)
            UserViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chat = list[position]
        if (holder is BotViewHolder) {
            holder.tvChatBot.text = chat.text
        } else if (holder is UserViewHolder) {
            holder.tvChatUser.text = chat.text
        }
    }

    override fun getItemCount(): Int = list.size

    inner class BotViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvChatBot: TextView = view.findViewById(R.id.tvChatBot)
    }

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvChatUser: TextView = view.findViewById(R.id.tvChatUser)
    }
}
