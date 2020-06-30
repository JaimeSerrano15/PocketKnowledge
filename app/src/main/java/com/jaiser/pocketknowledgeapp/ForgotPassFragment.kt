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
