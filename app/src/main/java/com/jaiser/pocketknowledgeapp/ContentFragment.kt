package com.jaiser.pocketknowledgeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jaiser.pocketknowledgeapp.databinding.FragmentContentBinding
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass.
 */
class ContentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentContentBinding>(
            inflater,
            R.layout.fragment_content,
            container,
            false
        )

        val args = ContentFragmentArgs.fromBundle(arguments!!)

        setup(args.lessionTittle)

        when (args.subject) {
            "math" -> binding.lessonTv.visibility = View.VISIBLE
            "leng" -> binding.lessonTv.visibility = View.VISIBLE
            "soc" -> {

                binding.photoView.setImageResource(R.drawable.soc1)
                binding.photoView2.setImageResource(R.drawable.soc2)
            }
            "cienc" -> binding.lessonTv.visibility = View.VISIBLE
        }

        return binding.root
    }

    fun setup(lesson_title : String){
        (activity as AppCompatActivity).supportActionBar?.title = lesson_title
    }

}
