package com.jaiser.pocketknowledgeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.jaiser.pocketknowledgeapp.databinding.FragmentContentBinding

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

        //binding.lessonTv.text = "ESTA ES LA LECCIÓN ${args.lessonId}"

        binding.photoView.setImageResource(R.drawable.soc1)
        binding.photoView2.setImageResource(R.drawable.soc2)


        return binding.root
    }

}
