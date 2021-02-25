package com.example.safesend.ui.home

import android.app.Application
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.safesend.R
import com.example.safesend.Utility.SMS
import com.example.safesend.Utility.SMSApplication
import com.example.safesend.adapters.MessagesAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MessagesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recycleAdapter: MessagesAdapter
    private lateinit var fab: FloatingActionButton
    private val smsViewModel: MessagesViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        recycleAdapter = MessagesAdapter()
        smsViewModel.allInbox.observe(viewLifecycleOwner, Observer {
            recycleAdapter.setData(it)
        })
        return  inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rc_view)
        recyclerView.adapter = recycleAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        fab = view.findViewById(R.id.fab2)
        fab.setOnClickListener { _ ->
//            val s = SMS(0,"Nabek","has it worked yet?")
//            smsViewModel.insert(s)
        }
    }

}