package com.jaiser.pocketknowledgeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.jaiser.pocketknowledgeapp.databinding.FragmentConfiguracionBinding

/**
 * A simple [Fragment] subclass.
 */
class Configuracion : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var binding = DataBindingUtil.inflate<FragmentConfiguracionBinding>(
            inflater,
            R.layout.fragment_configuracion, container, false
        )

        navigateListeners(binding)

        setup()

        return binding.root
    }

    fun setup() {
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.settings_bar_title)
    }

    fun navigateListeners(binding: FragmentConfiguracionBinding) {
        binding.changePassword.setOnClickListener { view ->
            view.findNavController()
                .navigate(ConfiguracionDirections.actionConfiguracionToChangePassFragment())
        }

        binding.signOut.setOnClickListener { view ->
            FirebaseAuth.getInstance().signOut()
            view.findNavController()
                .navigate(ConfiguracionDirections.actionConfiguracionToLoginFragment())
        }
        binding.sendFeedback.setOnClickListener { view ->
            Toast.makeText(this.context, "Proximamente!!", Toast.LENGTH_SHORT).show()
        }
    }

}
