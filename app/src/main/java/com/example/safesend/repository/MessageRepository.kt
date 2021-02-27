package com.example.safesend.repository

import android.Manifest
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.safesend.Utility.SMS
import com.example.safesend.db.DaoSMS
import com.example.safesend.db.SmsDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


object MessageRepository {
    private lateinit var messageDao: DaoSMS
    private val scope = CoroutineScope(SupervisorJob())
    lateinit var allMessages: LiveData<List<SMS>>
    operator fun invoke(context: Context): MessageRepository {
        messageDao = SmsDatabase.getDatabase(context, scope).smsDao();
        allMessages = messageDao.getAllSms().asLiveData()
        return this
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(sms: SMS) {
        messageDao.insertAll(sms)
    }

    fun readSms(context: Context): List<SMS>{
        val col_projection = arrayOf("_id", "address", "body")
        val cursor: Cursor? = context.contentResolver.query(Uri.parse("content://sms/inbox"), col_projection, null, null, "_id DESC")
        val inboxSms = ArrayList<SMS>()
        if (cursor?.moveToFirst() == true) { // must check the result to prevent exception
            do {
                val id: String = cursor.getString(0)
                val address: String = cursor.getString(1)
                val body: String = cursor.getString(2)
                val smsInbox = SMS(0, address, body)
                inboxSms.add(smsInbox)
            } while (cursor.moveToNext())
        }
        cursor?.close()
        return inboxSms
    }


}

