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
    private lateinit var auth : FirebaseAuth

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

        binding.registerButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }


        //setLoginLogic(binding)

        //navigationListener(binding)

        setup()

        Toast.makeText(this.context, "${FirebaseAuth.getInstance().currentUser?.email.toString()}", Toast.LENGTH_SHORT ).show()

        return binding.root
    }


    fun setup() {
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.pocket_knowledge_bar_title)
    }


    private fun showErrorMessage() {
        Toast.makeText(activity, "Algo salió mal", Toast.LENGTH_SHORT)
    }

    fun setLoginLogic(binding: FragmentLoginBinding) {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.loginButton.setOnClickListener {

            viewModel.authenticate(
                binding.mailLoginLayout?.editText?.text.toString(),
                binding.passwordLoginLayout?.editText?.text.toString()
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
                    Toast.makeText(this.context, "Bienvenido!", Toast.LENGTH_SHORT).show()
                    val action = LoginFragmentDirections.actionLoginFragmentToHome2(
                        viewModel.username,
                        viewModel.password
                    )
                    NavHostFragment.findNavController(this).navigate(action)
                }
                LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION -> Toast.makeText(this.context, "Credenciales erroneas", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun navigationListener(binding: FragmentLoginBinding) {

        binding.registerButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    fun signInUser(binding: FragmentLoginBinding){
        val email = binding.mailLoginLayout?.editText?.text.toString()
        val password = binding.passwordLoginLayout?.editText?.text.toString()

        if(email.isEmpty()){
            binding.mailLoginLayout.error = "Por favor ingrese su correo"
            binding.mailLoginLayout.requestFocus()
            return
        }

        if (password.isEmpty()){
            binding.passwordLoginLayout.error = "Por favor ingrese su contraseña"
            binding.passwordLoginLayout.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener() { task ->
            if (task.isSuccessful){
                NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_home2)
                Toast.makeText(this.context, "BIENVENIDO!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this.context, "Credenciales Incorrectas", Toast.LENGTH_SHORT).show()
            }
        }

    }

}