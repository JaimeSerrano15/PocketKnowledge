package com.jaiser.pocketknowledgeapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.jaiser.pocketknowledgeapp.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class Home : Fragment() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home, container, false
        )

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        val args = HomeArgs.fromBundle(arguments!!)

        if (args.user != "-" && args.pass != "-") {
            viewModel.authenticate(args.user, args.pass)
        } else {

        }

        val navController = findNavController()

        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                LoginViewModel.AuthenticationState.UNAUTHENTICATED -> navController.navigate(R.id.loginFragment)
            }
        })

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




        return binding.root
    }

    fun setup() {
        activity?.setTitle("PocketKnowledge")
    }


}
