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

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.jaiser.pocketknowledgeapp.databinding.FragmentFavBinding
import com.jaiser.pocketknowledgeapp.databinding.FragmentLessonBinding

/**
 * A simple [Fragment] subclass.
 */
class FavFragment : Fragment() {

    val db = FirebaseFirestore.getInstance()

    private lateinit var userID : String
    private lateinit var auth : FirebaseAuth;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

     var binding = DataBindingUtil.inflate<FragmentFavBinding>(inflater,
     R.layout.fragment_fav, container, false)

        auth = FirebaseAuth.getInstance()
        userID = auth.currentUser!!.uid

        setup()

        Log.d("TAG", "a punto de ir a buscar lso favoritos")
        getFavs(binding)

        return binding.root;
    }

    fun setup() {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.favorites_bar_title)
    }

    fun getFavs(binding : FragmentFavBinding){
        Log.d("TAG","Entrado a la primera funcion")
        var currentUser = db.collection("users").document(userID)

        currentUser.get().addOnSuccessListener{document->
            Log.d("TAG", "Entrando a la base de datos")

            var favorites = document.get("fav") as ArrayList<HashMap<String, String>>
            for(h in favorites) {
                var name = h["lesson"]!!
                var level = h["nivel"]!!
                var id = h["id"]!!

                Log.d("TAG", "Se han obtenido lso datos: ${name} , ${level} , ${id}")

                dynamicButtons(binding, level, id.toInt(), name)
                }
            }
        }

   fun dynamicButtons(
        binding: FragmentFavBinding,
        level : String,
        id : Int,
        name: String
    ) {
        val layout = binding.favLayout


        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        params.setMargins(32, 24, 32, 24)

        val btn = Button(this.context)

        /*Lesson Button*/
        btn.setBackgroundResource(R.drawable.rounded_button)
        btn.text = name
        btn.setTextColor(Color.WHITE)

        btn.setOnClickListener {
            Toast.makeText(
                this.context,
                "Puedes hacer Zoom en las lecciones!",
                Toast.LENGTH_SHORT
            ).show()
            var lesson_title: String = btn.text.toString()
            val action = FavFragmentDirections.actionFavFragmentToContentFragment(
                id,
                lesson_title,
                level)
            NavHostFragment.findNavController(this).navigate(action)
        }


        layout.addView(btn, params)
    }

    }

