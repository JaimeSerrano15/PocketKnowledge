package com.jaiser.pocketknowledgeapp

import android.annotation.SuppressLint
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
import com.google.firebase.firestore.FirebaseFirestore
import com.jaiser.pocketknowledgeapp.databinding.FragmentLessonBinding

/**
 * A simple [Fragment] subclass.
 */
class LessonFragment : Fragment() {

    val db = FirebaseFirestore.getInstance()

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentLessonBinding>(
            inflater,
            R.layout.fragment_lesson,
            container,
            false
        )

        val args = LessonFragmentArgs.fromBundle(arguments!!)

        setLesson(args, binding)

        /*db.collection("mathone").get().addOnSuccessListener { result ->
            for (document in result) {
                Log.i("info", "${document.id} => ${document.get("name")}")
                dynamicButtons(binding, args, document.id.toInt(), document.get("name").toString())
            }
        }
            .addOnFailureListener { exception ->
                Log.i("info", "Error getting documents: ", exception)
            }*/

        //dynamicButtons(binding, args)

        setup()

        return binding.root
    }

    fun setup() {
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.lesson_bar_title)
    }

    fun dynamicButtons(
        binding: FragmentLessonBinding,
        args: LessonFragmentArgs,
        id: Int,
        name: String
    ) {
        val layout = binding.lessonLayout

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        params.setMargins(32, 24, 32, 24)

        val btn = Button(this.context)
        val favBtn = Button(this.context)

        /*Lesson Button*/
        btn.setBackgroundResource(R.drawable.rounded_button)
        btn.id = id
        btn.text = name
        btn.setTextColor(Color.WHITE)

        btn.setOnClickListener {
            Toast.makeText(
                this.context,
                "Puedes hacer Zoom en las lecciones!",
                Toast.LENGTH_SHORT
            ).show()
            var lesson_title: String = btn.text.toString()
            val action = LessonFragmentDirections.actionLessonFragmentToContentFragment(
                id,
                args.subject,
                lesson_title,
                args.level
            )
            NavHostFragment.findNavController(this).navigate(action)
        }

        layout.addView(btn, params)
    }

    private fun setLesson(args: LessonFragmentArgs, binding: FragmentLessonBinding){
        if(args.subject == "math"){
            when (args.level) {
                "mathone" -> getLessonDB("mathone", binding, args)
                "mathtwo" -> getLessonDB("mathtwo", binding, args)
                "maththree" -> getLessonDB("maththree", binding, args)
            }
            return
        }

        if(args.subject == "leng"){
            when (args.level) {
                "lengone" -> getLessonDB("lengone", binding, args)
                "lengtwo" -> getLessonDB("lengtwo", binding, args)
                "lengthree" -> getLessonDB("lengthree", binding, args)
            }
            return
        }

        if(args.subject == "soc"){
            when (args.level) {
                "socone" -> getLessonDB("socone", binding, args)
                "soctwo" -> getLessonDB("soctwo", binding, args)
                "socthree" -> getLessonDB("socthree", binding, args)
            }
            return
        }

        if(args.subject == "cienc"){
            when (args.level) {
                "ciencone" -> getLessonDB("ciencone", binding, args)
                "cienctwo" -> getLessonDB("cienctwo", binding, args)
                "ciencthree" -> getLessonDB("ciencthree", binding, args)
            }
            return
        }
    }

    private fun getLessonDB(collecion : String, binding : FragmentLessonBinding, args: LessonFragmentArgs){
        db.collection(collecion).get().addOnSuccessListener { result ->
            for (document in result) {
                Log.i("info", "${document.id} => ${document.get("name")}")
                dynamicButtons(binding, args, document.id.toInt(), document.get("name").toString())
            }
        }
            .addOnFailureListener { exception ->
                Log.i("info", "Error getting documents: ", exception)
            }
    }

}
