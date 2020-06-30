package com.jaiser.pocketknowledgeapp

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.jaiser.pocketknowledgeapp.databinding.FragmentRegisterBinding
import kotlin.collections.HashMap

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment() {

    private lateinit var auth : FirebaseAuth;
    private lateinit var fStore : FirebaseFirestore;
    private lateinit var userID : String;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentRegisterBinding>(inflater, R.layout.fragment_register, container, false)
        setup()

        binding.registeredButton.setOnClickListener{view: View ->
            signUpUser(binding)
            //view.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        auth = FirebaseAuth.getInstance()
        fStore = FirebaseFirestore.getInstance()

        return binding.root
    }

    fun setup() {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.register_bar_title)
    }

    fun signUpUser(binding: FragmentRegisterBinding) {
        val email = binding.etRegisterMail?.editText?.text.toString()
        val password = binding.etRegisterPassword?.editText?.text.toString()
        val fav : ArrayList<String> = ArrayList<String>()
        fav.add("")

        if(email.isEmpty()){
            binding.etRegisterMail.error = "Por favor ingrese su correo."
            binding.etRegisterMail.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etRegisterMail.error = "Ingrese un correo inválido"
            binding.etRegisterMail.requestFocus()
            return
        }

        if(password.isEmpty()){
            binding.etRegisterPassword.error = "Por favor ingrese su contraseña."
            binding.etRegisterPassword.requestFocus()
            return
        }

        Log.i("info", "Aquiiii $email and $password")

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener() { task ->
            if (task.isSuccessful){
                userID = auth.currentUser!!.uid
                var documentReference : DocumentReference = fStore.collection("users").document(userID)
                var user : User = User()
                user.email = email;
                user.password = password;
                user.fav = fav
                documentReference.set(user).addOnSuccessListener( OnSuccessListener<Void>() {
                    fun OnSucess(aVoid : Void){
                        Log.d("Created", "OnSucess: The new profile has been created succesfully")
                    }
                }
                )
                Toast.makeText(this.context, "Usuario creado correctamente", Toast.LENGTH_SHORT).show()
                NavHostFragment.findNavController(this).navigate(R.id.action_registerFragment_to_loginFragment)
            } else {
                Toast.makeText(this.context, "No se ha logrado crear el usuario", Toast.LENGTH_SHORT).show()
            }
        }

        return
    }


}
