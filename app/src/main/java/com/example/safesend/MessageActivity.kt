package com.example.safesend

import android.app.ActionBar
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.safesend.Utility.SMS
import com.example.safesend.adapters.DetailMessageAdapter
import com.example.safesend.adapters.MessagesAdapter

class MessageActivity : AppCompatActivity() {
    private var recycler: RecyclerView? = null
    private var recycleAdapter: DetailMessageAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)
        setSupportActionBar(findViewById(R.id.toolbar))

        val ir = intent.extras?.get("Sender")
        recycleAdapter = DetailMessageAdapter()
        recycler = findViewById(R.id.detail_rc_)
        recycler?.adapter = recycleAdapter
        recycler?.layoutManager = LinearLayoutManager(applicationContext)
        getIndividualSms(ir.toString())
    }

    private fun getIndividualSms(sender: String){
        val col_projection = arrayOf("_id", "address", "body")
        val selectionClause = "address IN (?)"
        val sa = arrayOf(sender)
        val cursor: Cursor? = applicationContext.contentResolver.query(Uri.parse("content://sms/inbox"), col_projection, selectionClause, sa, "_id DESC")
        val singleUser = ArrayList<SMS>()
        if (cursor?.moveToFirst() == true) { // must check the result to prevent exception
            do {
                val id: String = cursor.getString(0)
                val address: String = cursor.getString(1)
                val body: String = cursor.getString(2)
                val smsInbox = SMS(0, address, body)
                singleUser.add(smsInbox)
                Log.i("Messages: ", "$address $body")
            } while (cursor.moveToNext())
        }
        recycleAdapter?.setData(singleUser)
        cursor?.close()
    }
}