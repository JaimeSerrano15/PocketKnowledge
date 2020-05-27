package com.jaiser.pocketknowledgeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.jaiser.pocketknowledgeapp.databinding.FragmentLevelBinding

/**
 * A simple [Fragment] subclass.
 */
class LevelFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentLevelBinding>(inflater, R.layout.fragment_level, container, false)

        val args = LevelFragmentArgs.fromBundle(arguments!!)



        binding.basicButton.setOnClickListener{view:View ->
            view.findNavController().navigate(LevelFragmentDirections.actionLevelFragmentToLessonFragment(args.subject))
        }

        binding.interButton.setOnClickListener { view ->
            Toast.makeText(this.context, "Pronto estará disponible! :)", Toast.LENGTH_SHORT).show()
        }

        binding.hardButton.setOnClickListener { view ->
            Toast.makeText(this.context, "Pronto estará disponible! :)", Toast.LENGTH_SHORT).show()
        }

        setup(args.subject)

        return binding.root
    }

    fun setup(subject : String){
        when(subject){
            "math" -> (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.math_bar_title)
            "cienc" -> (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.science_bar_title)
            "soc" -> (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.soc_bar_title)
            "leng" -> (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.leng_bar_title)
        }
    }



}
