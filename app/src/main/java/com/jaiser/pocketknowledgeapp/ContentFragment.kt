package com.jaiser.pocketknowledgeapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.github.chrisbanes.photoview.PhotoView
import com.jaiser.pocketknowledgeapp.databinding.FragmentContentBinding
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass.
 */
class ContentFragment : Fragment() {

    val infoList = mutableListOf(R.drawable.soc1, R.drawable.soc2)

    @SuppressLint("ResourceAsColor")
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

        showContent(binding, args)

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.fav_menu,menu)
    }

    fun setup(lesson_title: String) {
        (activity as AppCompatActivity).supportActionBar?.title = lesson_title
    }

    fun showContent(binding: FragmentContentBinding, args: ContentFragmentArgs) {
        when (args.subject) {
            "math" -> {
                binding.photoView.setImageResource(R.drawable.ma1)
                binding.photoView2.setImageResource(R.drawable.ma2)
            }
            "leng" -> {
                binding.photoView.setImageResource(R.drawable.leng1)
                binding.photoView2.setImageResource(R.drawable.leng2)
            }
            "soc" -> {
                binding.photoView.setImageResource(R.drawable.soc1)
                binding.photoView2.setImageResource(R.drawable.soc2)
            }
            "cienc" -> {
                binding.photoView.setImageResource(R.drawable.cien)
                binding.photoView2.visibility = View.GONE
            }
        }
    }

    private fun setUpLesson(subjet: String, binding: FragmentContentBinding) {
        val layout_content = binding.images

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )

        val line_params =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 5)

        val question_params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        val answer_params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        params.setMargins(5, 5, 5, 5)

        for (i in 1..2) {
            val photo = PhotoView(this.context)

            photo.id = i

            val line = View(this.context)
            line.setBackgroundColor(resources.getColor(R.color.my_dark_gray))


            photo.setImageResource(infoList[i - 1])

            layout_content?.addView(photo, params)
            layout_content?.addView(line, line_params)
        }

        val question_text = TextView(this.context)
        question_text.text = "Pregunta de Prueba"
        layout_content?.addView(question_text)

        val question = RadioGroup(this.context)
        for (i in 0..2) {
            val option = RadioButton(this.context)
            option.text = "Respuesta $i"
            option.id = i

            question.addView(option, answer_params)
        }


        layout_content?.addView(question, question_params)

        val verification = Button(this.context)
        verification.text = "Verificar"

        layout_content?.addView(verification)
    }

}
