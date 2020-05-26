package com.jaiser.pocketknowledgeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.jaiser.pocketknowledgeapp.databinding.FragmentInformacionBinding

/**
 * A simple [Fragment] subclass.
 */
class Informacion : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      var binding = DataBindingUtil.inflate<FragmentInformacionBinding>(inflater, R.layout.fragment_informacion, container, false)

        setup()

        return binding.root
    }

    fun setup(){
        activity?.setTitle("Quiénes somos")
    }

}
