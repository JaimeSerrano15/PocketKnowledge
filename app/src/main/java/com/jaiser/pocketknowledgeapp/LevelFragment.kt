package com.jaiser.pocketknowledgeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        return binding.root
    }

}
