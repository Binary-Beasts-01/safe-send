package com.example.safesend.repository

import com.example.safesend.models.MessageModel

class MessageRepository {
    private var messageRepository: MessageRepository? = null;
    private var messageList: ArrayList<MessageModel>? = null;

    fun getModelInstance(): MessageRepository {
        if(messageRepository != null){
            return messageRepository as MessageRepository
        }
        messageRepository = MessageRepository()
        return messageRepository as MessageRepository
    }

    fun populateMessage(): ArrayList<MessageModel>?{
        var m: MessageModel
        for(i in 1..4){
            m = MessageModel()
            m.sender = "Melkamu"
            m.msgContent = "Hey yet hedeh new?"
            messageList?.add(m)
        }
        return messageList
    }

}