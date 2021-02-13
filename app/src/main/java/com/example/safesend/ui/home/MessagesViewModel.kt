package com.example.safesend.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.safesend.models.MessageModel
import com.example.safesend.repository.MessageRepository

class MessagesViewModel : ViewModel() {
    var messageData = MutableLiveData<MutableList<MessageModel>>();

    init{
        messageData.value = MessageRepository.getMessages()
    }

    fun getAllMessages(): MutableList<MessageModel> {
        return messageData.value as MutableList<MessageModel>
    }
    fun listenForMessage(): LiveData<MutableList<MessageModel>> {
        return messageData
    }
    fun addMessage(){
        val msg = MessageRepository.getModel("Zeleke", "Tesfaye")
        val msgList = messageData.value
        msgList?.add(msg)
        messageData.postValue(msgList!!)
    }

}