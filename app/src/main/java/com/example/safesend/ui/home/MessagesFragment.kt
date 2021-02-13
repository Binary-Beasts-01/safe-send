package com.example.safesend.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.safesend.R
import com.example.safesend.adapters.MessagesAdapter
import com.example.safesend.models.MessageModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MessagesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recycleAdapter: MessagesAdapter
    private lateinit var fab: FloatingActionButton
    private lateinit var viewModel: MessagesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(MessagesViewModel::class.java)
        recycleAdapter = MessagesAdapter(viewModel.getAllMessages(), activity?.applicationContext as Context)
        viewModel.listenForMessage().observe(viewLifecycleOwner, Observer<List<MessageModel>>{ users ->
            recycleAdapter?.notifyDataSetChanged()
            Toast.makeText(activity, "Data changed", Toast.LENGTH_SHORT).show()
        })

        return  inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rc_view)
        recyclerView?.adapter = recycleAdapter
        recyclerView?.layoutManager = LinearLayoutManager(activity?.applicationContext)
        fab = view.findViewById(R.id.fab2)
        fab?.setOnClickListener { view ->
            viewModel.addMessage()
        }

    }
}