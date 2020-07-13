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
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.jaiser.pocketknowledgeapp.databinding.FragmentChangePassBinding

/**
 * A simple [Fragment] subclass.
 */
class ChangePassFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setup()
        val binding = DataBindingUtil.inflate<FragmentChangePassBinding>(
            inflater,
            R.layout.fragment_change_pass,
            container,
            false
        )

        auth = FirebaseAuth.getInstance()

        binding.btnChange.setOnClickListener { view ->
            changePass(binding)
        }


        return binding.root
    }

    fun setup() {
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.pocket_knowledge_bar_title)
    }

    private fun changePass(binding: FragmentChangePassBinding) {
        val oldPass = binding.etOldPassword?.editText?.text.toString()
        val newPass = binding.etNewPassword?.editText?.text.toString()

        if (oldPass.isNotEmpty() && newPass.isNotEmpty()) {

            val user = auth.currentUser

            if (user != null && user.email != null) {
                val credential = EmailAuthProvider
                    .getCredential(user.email!!, oldPass)
// Prompt the user to re-provide their sign-in credentials
                user?.reauthenticate(credential)
                    ?.addOnCompleteListener {
                        if (it.isSuccessful) {
                            user?.updatePassword(newPass)
                                ?.addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(
                                            this.context,
                                            "Se actualizado la contraseña",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        auth.signOut()
                                        NavHostFragment.findNavController(this)
                                            .navigate(R.id.loginFragment)
                                    } else {
                                        Toast.makeText(
                                            this.context,
                                            "Algo salió mal!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                        } else {
                            Toast.makeText(this.context, "Algo salió mal!", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            }
        } else {
            Toast.makeText(this.context, "Por favor llene todos los espacios", Toast.LENGTH_SHORT)
                .show()
        }
    }

}
