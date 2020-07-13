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

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
        setup()

        binding.sendButton.setOnClickListener { view ->
            val destination = "empresaurios2020@gmail.com"
            val subject = binding.subjectEt.text.toString()
            val message = binding.messageEt.text.toString()
            sendEmail(destination, subject, message, binding)
        }



        return binding.root
    }

    private fun sendEmail(destination: String, subject: String, message: String, binding: FragmentEmailBinding) {
        val mIntent = Intent(Intent.ACTION_SEND)

        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"

        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(destination))
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        mIntent.putExtra(Intent.EXTRA_TEXT, message)

        try {
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
            binding.subjectEt.text.clear()
            binding.messageEt.text.clear()
        } catch (e: Exception) {
            Toast.makeText(this.context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun setup(){
        (activity as AppCompatActivity).supportActionBar?.title = "Comentarios"
    }

}
