package com.example.safesend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class MessageAreaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.message_area)

        val arr = MutableLiveData<Array<String>>();
        arr.observe(this, Observer {
//            update the recycler view when data changes
        })
    }
}