package com.jaiser.pocketknowledgeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.jaiser.pocketknowledgeapp.databinding.FragmentRegisterBinding

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentRegisterBinding>(inflater, R.layout.fragment_register, container, false)
        setup()

        binding.registeredButton.setOnClickListener{view: View ->
            view.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        return binding.root
    }

    fun setup() {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.register_bar_title)
    }

}
