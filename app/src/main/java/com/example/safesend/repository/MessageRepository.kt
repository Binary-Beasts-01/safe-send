package com.example.safesend.repository

import androidx.lifecycle.MutableLiveData
import com.example.safesend.models.MessageModel

object MessageRepository{
    var messageList = mutableListOf(getModel("Melkamu","Hey yet hedeh new?"))

    fun getMessages(): MutableList<MessageModel> {

        val data = MutableLiveData<MutableList<MessageModel>>()
        populateMessage()
        data.postValue(messageList)
        return  messageList
    }
    fun getModel(sender: String, content: String): MessageModel{
        val model = MessageModel(sender, content)
        return model
    }
    fun populateMessage(){
        for(i in 1..4){
            val model = getModel("Melkamu","Hey yet hedeh new?")
            messageList.add(model)
        }
    }
}



