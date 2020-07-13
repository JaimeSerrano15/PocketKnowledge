/*
    Pocket Knowledge. Application for educational purposes
    Copyright (C) 2020. Empresaurios

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

    Contact: empresaurios2020@gmail.com
 */
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
            Toast.makeText(this.context, "De momento está vacío! :(", Toast.LENGTH_SHORT).show()
            //view.findNavController().navigate(GuiasDirections.actionGuiasToGuideContentFragment())
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
