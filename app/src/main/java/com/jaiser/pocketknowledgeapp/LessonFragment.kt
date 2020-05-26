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

        val layout = binding.lessonLayout
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        params.setMargins(32,24,32,24)

        for(i in 1..10){
            val btn = Button(this.context)

            btn.setBackgroundResource(R.drawable.rounded_button)
            btn.id = i
            btn.text = "Lección $i"
            btn.setTextColor(Color.WHITE)

            btn.setOnClickListener{
                val action = LessonFragmentDirections.actionLessonFragmentToContentFragment(i)
                NavHostFragment.findNavController(this).navigate(action)
            }

            layout.addView(btn, params)
        }

        return binding.root
    }

}
