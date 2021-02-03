package com.example.safesend.ui.blocked

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.safesend.R

class BlockedFragment : Fragment() {

    private lateinit var blockedViewModel: BlockedViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        blockedViewModel =
                ViewModelProvider(this).get(BlockedViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_blocked, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        blockedViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        setHasOptionsMenu(true)
        return root
    }


}