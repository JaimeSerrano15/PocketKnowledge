package com.jaiser.pocketknowledgeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jaiser.pocketknowledgeapp.databinding.FragmentFavBinding

/**
 * A simple [Fragment] subclass.
 */
class FavFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

     var binding = DataBindingUtil.inflate<FragmentFavBinding>(inflater,
     R.layout.fragment_fav, container, false)
        setup()

        return binding.root;
    }

    fun setup() {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.favorites_bar_title)
    }


}
