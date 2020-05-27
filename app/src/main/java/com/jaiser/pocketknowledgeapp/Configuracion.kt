package com.jaiser.pocketknowledgeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jaiser.pocketknowledgeapp.databinding.FragmentConfiguracionBinding

/**
 * A simple [Fragment] subclass.
 */
class Configuracion : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var binding = DataBindingUtil.inflate<FragmentConfiguracionBinding>(inflater,
            R.layout.fragment_configuracion, container,false)

        setup()

        return binding.root
    }

    fun setup() {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.settings_bar_title)
    }

}
