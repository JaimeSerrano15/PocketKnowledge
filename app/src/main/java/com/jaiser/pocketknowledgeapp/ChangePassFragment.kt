package com.jaiser.pocketknowledgeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

/**
 * A simple [Fragment] subclass.
 */
class ChangePassFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setup()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_pass, container, false)
    }

    fun setup() {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.pocket_knowledge_bar_title)
    }

}
