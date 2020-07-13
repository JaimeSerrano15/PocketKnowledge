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
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import com.jaiser.pocketknowledgeapp.databinding.FragmentForgotPassBinding

/**
 * A simple [Fragment] subclass.
 */
class ForgotPassFragment : Fragment() {

    private lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentForgotPassBinding>(
            inflater,
            R.layout.fragment_forgot_pass,
            container,
            false
        )

        setup()

        auth = FirebaseAuth.getInstance()

        binding.sendForgotBt.setOnClickListener { view ->
            sendPassdwordEmail(binding)
        }

        return binding.root
    }

    private fun sendPassdwordEmail(binding: FragmentForgotPassBinding){
        var email = binding.mailForgotLy?.editText?.text.toString()

        if (email.isNotEmpty()){
            auth.sendPasswordResetEmail(binding.mailForgotLy?.editText?.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this.context, "Recuperación enviada al correo", Toast.LENGTH_SHORT).show()
                        NavHostFragment.findNavController(this).navigate(R.id.loginFragment)
                    } else {
                        Toast.makeText(this.context, "Ha ocurrido un error. Intente nuevamente.", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(this.context, "Ingrese su correo por favor", Toast.LENGTH_SHORT).show()
        }

    }

    fun setup() {
        (activity as AppCompatActivity).supportActionBar?.title = "Recuperar Contraseña"
    }

}
