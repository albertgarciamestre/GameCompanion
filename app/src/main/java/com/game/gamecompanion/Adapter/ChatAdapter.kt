package com.game.gamecompanion.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.game.gamecompanion.model.ChatModel
import com.game.gamecompanion.R

import kotlinx.android.synthetic.main.chat.view.*
import kotlinx.android.synthetic.main.chat.view.*

class ChatAdapter (var list: List <ChatModel>): RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val textview = itemView.mensajeMensaje
        val nameView = itemView.nombreMensaje
        val timeView = itemView.horaMensaje

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.textview.text = list[position].text
        holder.nameView.text = list[position].name
        holder.timeView.text = list[position].createdAt
    }

}