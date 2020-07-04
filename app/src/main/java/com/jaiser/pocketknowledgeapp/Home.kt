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
