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
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.jaiser.pocketknowledgeapp.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.view.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,
            R.layout.fragment_login, container, false
        )

        auth = FirebaseAuth.getInstance()

        binding.loginButton.setOnClickListener { view ->
            signInUser(binding)
        }

        binding.RegisterTxt?.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.forgotPassword?.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_loginFragment_to_forgotPassFragment)
        }

        setup()

        return binding.root
    }


    fun setup() {
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.pocket_knowledge_bar_title)
    }


    fun signInUser(binding: FragmentLoginBinding) {
        val email = binding.mailLoginLayout?.editText?.text.toString()
        val password = binding.passwordLoginLayout?.editText?.text.toString()

        if (email.isEmpty()) {
            binding.mailLoginLayout.error = "Por favor ingrese su correo"
            binding.mailLoginLayout.requestFocus()
            return
        }

        if (password.isEmpty()) {
            binding.passwordLoginLayout.error = "Por favor ingrese su contraseña"
            binding.passwordLoginLayout.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener() { task ->
            if (task.isSuccessful){
              //  NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_home2)
                val action = LoginFragmentDirections.actionLoginFragmentToHome2(password, email)
                NavHostFragment.findNavController(this).navigate(action)
                Toast.makeText(this.context, "BIENVENIDO!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this.context, "Credenciales Incorrectas", Toast.LENGTH_SHORT).show()
            }
        }

    }

}