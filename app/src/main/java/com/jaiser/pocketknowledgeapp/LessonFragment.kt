package com.jaiser.pocketknowledgeapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
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
import com.jaiser.pocketknowledgeapp.databinding.FragmentLessonBinding

/**
 * A simple [Fragment] subclass.
 */
class LessonFragment : Fragment() {

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

        dynamicButtons(binding, args)

        setup()

        return binding.root
    }

    fun setup() {
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.lesson_bar_title)
    }

    fun dynamicButtons(binding: FragmentLessonBinding, args: LessonFragmentArgs) {
        val layout = binding.lessonLayout

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )


        params.setMargins(32, 24, 32, 24)

        for (i in 1..2) {
            val btn = Button(this.context)
            val favBtn = Button(this.context)

            /*Lesson Button*/
            btn.setBackgroundResource(R.drawable.rounded_button)
            btn.id = i
            btn.text = "Lección $i"
            btn.setTextColor(Color.WHITE)

            btn.setOnClickListener {
                Toast.makeText(
                    this.context,
                    "Puedes hacer Zoom en las lecciones!",
                    Toast.LENGTH_SHORT
                ).show()
                var lesson_title: String = btn.text.toString()
                val action = LessonFragmentDirections.actionLessonFragmentToContentFragment(
                    i,
                    args.subject,
                    lesson_title
                )
                NavHostFragment.findNavController(this).navigate(action)
            }

            layout.addView(btn, params)
        }
    }

}
