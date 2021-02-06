package com.example.safesend.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.safesend.models.MessageModel
import com.example.safesend.repository.MessageRepository

class MessageViewModel: ViewModel() {
    var messageData: MutableLiveData<MutableList<MessageModel>>? = null;

    fun initData(){
        if (messageData != null){
            return
        }
        messageData = MessageRepository.getMessages()
    }
    fun addToList(){
        val m: MutableList<MessageModel>? = messageData?.value
        val k = MessageModel()
        k.sender = "new one"
        k.msgContent = "please work"
        m?.add(k)
        messageData?.postValue(m)
    }
    fun getAllMessages(): LiveData<MutableList<MessageModel>>? {
        return messageData
    }
}