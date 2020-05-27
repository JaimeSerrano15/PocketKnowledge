package com.jaiser.pocketknowledgeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.jaiser.pocketknowledgeapp.databinding.FragmentGuiasBinding

/**
 * A simple [Fragment] subclass.
 */
class Guias : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = DataBindingUtil.inflate<FragmentGuiasBinding>(inflater, R.layout.fragment_guias, container, false)

        setup()

        binding.matematicaTextview.setOnClickListener { view ->
            view.findNavController().navigate(GuiasDirections.actionGuiasToGuideContentFragment())
        }

        binding.Ciencias.setOnClickListener {
            Toast.makeText(this.context, "De momento está vacío! :(", Toast.LENGTH_SHORT).show()
        }

        binding.lenguajeTextview.setOnClickListener {
            Toast.makeText(this.context, "De momento está vacío! :(", Toast.LENGTH_SHORT).show()
        }

        binding.Sociales.setOnClickListener {
            Toast.makeText(this.context, "De momento está vacío! :(", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    fun setup() {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.guides_bar_title)
    }

}
