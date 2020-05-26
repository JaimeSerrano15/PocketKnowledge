package com.jaiser.pocketknowledgeapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.jaiser.pocketknowledgeapp.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,
            R.layout.fragment_login, container, false
        )

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.loginButton.setOnClickListener {
            viewModel.authenticate(
                binding.emailLoginEditText.text.toString(),
                binding.passwordLoginEditText.text.toString()
            )
            Log.i("info", viewModel.authenticationState.value.toString())
        }
        val navController = findNavController()

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            viewModel.refuseAuthentication()
            navController.popBackStack(R.id.home2, false)
        }


        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                LoginViewModel.AuthenticationState.AUTHENTICATED -> {
                    val action = LoginFragmentDirections.actionLoginFragmentToHome2(
                        viewModel.username,
                        viewModel.password
                    )
                    NavHostFragment.findNavController(this).navigate(action)
                }
                LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION -> showErrorMessage()
            }
        })

        binding.registerButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        /* binding.loginButton.setOnClickListener{view: View ->
             view.findNavController().navigate(R.id.action_loginFragment_to_home2)
         }*/

        setup()

        return binding.root
    }

    fun setup() {
        activity?.setTitle("Bienvenido")
    }

    private fun showErrorMessage() {
        Toast.makeText(activity, "Algo salió mal", Toast.LENGTH_SHORT)
    }

}