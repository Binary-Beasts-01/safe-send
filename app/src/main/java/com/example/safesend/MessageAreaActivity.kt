package com.example.safesend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.safesend.adapters.MessagesAdapter
import com.example.safesend.view_models.MessageViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MessageAreaActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var recycleAdapter: MessagesAdapter? = null
    val msgList: MessageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.message_area)

        val fab: FloatingActionButton = findViewById(R.id.fab2)
        fab.setOnClickListener { view ->
            msgList.addToList()
        }

        recyclerView = findViewById(R.id.rc_view)
        msgList.initData()
        msgList.getAllMessages()?.observe(this, Observer {
            recycleAdapter?.notifyDataSetChanged()
            Toast.makeText(applicationContext, "Data changed", Toast.LENGTH_SHORT).show()
        })
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recycleAdapter = MessagesAdapter(msgList.getAllMessages()?.value, this)
        recyclerView?.adapter = recycleAdapter
        val layoutRecycle: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager = layoutRecycle
    }
}