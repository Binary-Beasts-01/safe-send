package com.example.safesend.repository

import android.Manifest
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import androidx.annotation.WorkerThread
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.safesend.Utility.SMS
import com.example.safesend.db.DaoSMS


class MessageRepository(private val smsDao: DaoSMS) {
    val messageList: LiveData<List<SMS>> = smsDao.getAllSms().asLiveData()
    var smsInboxList: MutableLiveData<List<SMS>> = MutableLiveData()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(sms: SMS) {
        smsDao.insertAll(sms)
    }
    fun readSms(context: Context): List<SMS>{
        val col_projection = arrayOf("_id", "address", "body")
        val cursor: Cursor? = context.contentResolver.query(Uri.parse("content://sms/inbox"), col_projection, null, null, "_id DESC")
        val inboxSms = ArrayList<SMS>()
        if (cursor?.moveToFirst() == true) { // must check the result to prevent exception
            do {
                for (idx in 0 until cursor.columnCount) {
                    val id: String = cursor.getString(0)
                    val address: String = cursor.getString(1)
                    val body: String = cursor.getString(2)
                    val smsInbox = SMS(0, address, body)
                    inboxSms.add(smsInbox)
                }
                // use msgData
            } while (cursor.moveToNext())
            smsInboxList.postValue(inboxSms)
        }
        cursor?.close()
        return inboxSms
    }
}

