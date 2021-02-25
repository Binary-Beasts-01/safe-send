package com.example.safesend.ui.home

import android.app.Application
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.util.Log
import androidx.lifecycle.*
import com.example.safesend.Utility.SMS
import com.example.safesend.db.DaoSMS
import com.example.safesend.db.SmsDatabase
import com.example.safesend.repository.MessageRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


class MessagesViewModel(private val app: Application) : AndroidViewModel(app) {

    val allInbox: MutableLiveData<List<SMS>> = MutableLiveData()
    init {
        allInbox.postValue(getMs())
    }
    private fun getMs(): ArrayList<SMS>{
        val col_projection = arrayOf("_id", "address", "body")
        val cursor: Cursor? = app.applicationContext.contentResolver.query(Uri.parse("content://sms/inbox"), col_projection, null, null, "_id DESC")
        val inboxSms = ArrayList<SMS>()
        if (cursor?.moveToFirst() == true) { // must check the result to prevent exception
            do {
                    val id: String = cursor.getString(0)
                    val address: String = cursor.getString(1)
                    val body: String = cursor.getString(2)
                    Log.i("Message num: $id", address)
                    val smsInbox = SMS(0, address, body)
                    inboxSms.add(smsInbox)
//                }
                // use msgData
            } while (cursor.moveToNext())
        }
        cursor?.close()
        return inboxSms
    }
}