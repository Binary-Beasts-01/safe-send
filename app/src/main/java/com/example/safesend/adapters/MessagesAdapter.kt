package com.example.safesend.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.safesend.R
import com.example.safesend.models.MessageModel
import kotlinx.android.synthetic.main.message_card.view.*

class MessagesAdapter(val messages: MutableList<MessageModel>, val context: Context): RecyclerView.Adapter<MessagesAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.message_card,parent,false)
        Log.i("Adapter on create", "addToList: ")
        return MessageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentMesg = messages.get(position)
        holder.sender.text = currentMesg.sender
        holder.content.contentDescription = currentMesg.msgContent
        Log.i("Adapter on bind", "addToList: ")
    }

    override fun getItemCount(): Int {
        return messages.size
    }


    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var sender: TextView = itemView.msg_sender
        var content: TextView = itemView.msg_content
    }
}