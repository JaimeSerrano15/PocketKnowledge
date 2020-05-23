package com.jaiser.pocketknowledgeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.jaiser.pocketknowledgeapp.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class Home : Fragment() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,
        R.layout.fragment_home, container, false)

        drawerLayout = binding.drawerLayout

        binding.btnMath.setOnClickListener{view: View->
            view.findNavController().navigate(R.id.action_home2_to_levelFragment)
        }

        return binding.root
    }



}
