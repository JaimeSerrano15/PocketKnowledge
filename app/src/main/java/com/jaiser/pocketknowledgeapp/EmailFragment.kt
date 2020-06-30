package com.jaiser.pocketknowledgeapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.jaiser.pocketknowledgeapp.databinding.FragmentEmailBinding
import java.lang.Exception
import javax.security.auth.Subject

/**
 * A simple [Fragment] subclass.
 */
class EmailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentEmailBinding>(
            inflater,
            R.layout.fragment_email,
            container,
            false
        )

        binding.sendButton.setOnClickListener { view ->
            val destination = "empresaurios2020@gmail.com"
            val subject = binding.subjectEt.text.toString()
            val message = binding.messageEt.text.toString()
            sendEmail(destination, subject, message)
            //Toast.makeText(this.context, "$subject , $message", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    private fun sendEmail(destination: String, subject: String, message: String) {
        val mIntent = Intent(Intent.ACTION_SEND)

        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"

        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(destination))
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        mIntent.putExtra(Intent.EXTRA_TEXT, message)

        try {
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
        } catch (e: Exception) {
            Toast.makeText(this.context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

}
