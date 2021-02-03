package com.example.safesend.ui.new_message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.safesend.R

class NewMessageFragment : Fragment() {

    private lateinit var newMessageViewModel: NewMessageViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        newMessageViewModel =
                ViewModelProvider(this).get(NewMessageViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_new_message, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        newMessageViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}