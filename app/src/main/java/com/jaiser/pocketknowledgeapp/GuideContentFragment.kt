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

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jaiser.pocketknowledgeapp.databinding.FragmentGuideContentBinding
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass.
 */
class GuideContentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentGuideContentBinding>(
            inflater,
            R.layout.fragment_guide_content,
            container,
            false
        )

        setup()

        showPdf(binding)

        return binding.root
    }



    private fun getName(): String {
        return "kotlin-quick-reference-sheet.pdf"
    }

    fun setup() {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.guides_bar_title)
    }

    fun showPdf(binding: FragmentGuideContentBinding){
        val pdfView = binding.pdfView



        pdfView.fromAsset(getName())
            .password(null)
            .defaultPage(0)
            .onPageError { page, _ ->
                Toast.makeText(this.context, "Error at page: $page", Toast.LENGTH_SHORT).show()
            }
            .load()
    }

}
