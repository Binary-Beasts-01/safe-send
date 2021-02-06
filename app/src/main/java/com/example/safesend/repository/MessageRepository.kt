package com.example.safesend.repository

import androidx.lifecycle.MutableLiveData
import com.example.safesend.models.MessageModel

object MessageRepository{
    private var messageList: ArrayList<MessageModel>
    init {
        messageList = ArrayList()
    }
    fun getMessages(): MutableLiveData<MutableList<MessageModel>>{
        populateMessage()
        val data = MutableLiveData<MutableList<MessageModel>>()
        data.value = messageList
        return  data
    }
    fun populateMessage(){
        var m: MessageModel
        for(i in 1..4){
            m = MessageModel()
            m.sender = "Melkamu"
            m.msgContent = "Hey yet hedeh new?"
            messageList.add(m)
        }
    }
}

//class MessageRepository {
//    private var messageList = ArrayList<MessageModel>()
//    private var messageRepository: MessageRepository? = null
//    constructor(){
//        getMessages()
//    }
//    fun getMessages(): MutableLiveData<MutableList<MessageModel>>{
//        populateMessage()
//        val data = MutableLiveData<MutableList<MessageModel>>()
//        data.value = messageList
//        return  data
//    }
//    fun populateMessage(){
//        var m: MessageModel
//        for(i in 1..4){
//            m = MessageModel()
//            m.sender = "Melkamu"
//            m.msgContent = "Hey yet hedeh new?"
//            messageList.add(m)
//        }
//    }
//}

