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
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.jaiser.pocketknowledgeapp.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class Home : Fragment() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var viewModel: LoginViewModel
    private lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home, container, false
        )

        val navController = findNavController()
        auth = FirebaseAuth.getInstance()

        if (auth.currentUser?.email == null){
            navController.navigate(R.id.loginFragment)
        }

        navigationListener(binding)

        setup()
        return binding.root
    }


    fun setup() {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.pocket_knowledge_bar_title)
    }



    fun navigationListener(binding: FragmentHomeBinding){
        binding.btnMath.setOnClickListener { view: View ->
            view.findNavController().navigate(HomeDirections.actionHome2ToLevelFragment("math"))
        }

        binding.btnLeng.setOnClickListener { view: View ->
            view.findNavController().navigate(HomeDirections.actionHome2ToLevelFragment("leng"))
        }

        binding.btnSoc.setOnClickListener { view: View ->
            view.findNavController().navigate(HomeDirections.actionHome2ToLevelFragment("soc"))
        }

        binding.btnCienc.setOnClickListener { view: View ->
            view.findNavController().navigate(HomeDirections.actionHome2ToLevelFragment("cienc"))
        }
    }



}
