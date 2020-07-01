package com.jaiser.pocketknowledgeapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import com.google.firebase.firestore.FirebaseFirestore
import com.jaiser.pocketknowledgeapp.databinding.FragmentContentBinding
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass.
 */
class ContentFragment : Fragment() {

    //val infoList = mutableListOf(R.drawable.soc1, R.drawable.soc2)
    var imagesList = arrayListOf<String>()
    var question = String()
    var answers = arrayListOf<String>()
    val db = FirebaseFirestore.getInstance()
     var favMark : Boolean =  false

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
        inflater?.inflate(R.menu.fav_menu, menu)

        changeFavoriteIcon(menu?.findItem(R.id.fav)!!)
       
    }

    fun changeFavoriteIcon(menuItem: MenuItem){
       var id = if(favMark) R.drawable.ic_favorite_black_24dp
        else R.drawable.ic_favorite_border_black_24dp

        menuItem.icon = ContextCompat.getDrawable(this.context!!, id)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
              R.id.fav -> {
                  favMark = !favMark
                  changeFavoriteIcon(item)
              }
        }
        return super.onOptionsItemSelected(item)
    }

    fun setup(lesson_title: String) {
        (activity as AppCompatActivity).supportActionBar?.title = lesson_title
    }

    fun showContent(binding: FragmentContentBinding, args: ContentFragmentArgs) {
        getData(args, binding)
        //setUpLesson(args.subject,binding)
        /*when (args.subject) {
            "math" -> {
                //binding.photoView.setImageResource(R.drawable.ma1)
                //binding.photoView2.setImageResource(R.drawable.ma2)
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
        }*/
    }

    private fun getData(args: ContentFragmentArgs, binding: FragmentContentBinding) {
        val docRef = db.collection(args.level).document(args.lessonId.toString())
        docRef.get().addOnSuccessListener { document ->
            if (document != null) {
                Toast.makeText(this.context, "IMAGENES CARGADAS", Toast.LENGTH_SHORT).show()
                imagesList = document.get("images") as ArrayList<String>
                question = document.get("question").toString()
                answers = document.get("ans") as ArrayList<String>
                setUpLesson(args.subject, binding)
            } else {
                Log.i("info", "No such document")
            }
        }
            .addOnFailureListener { exception ->
                Log.i("info", "get failed with ", exception)
            }
    }

    private fun setUpLesson(subjet: String, binding: FragmentContentBinding) {
        val index = imagesList.size
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

        for (i in 1..index) {
            val photo = PhotoView(this.context)

            photo.id = i

            val line = View(this.context)
            line.setBackgroundColor(resources.getColor(R.color.my_dark_gray))

            Glide.with(this)
                .load(imagesList[i - 1])
                .placeholder(R.drawable.placeholder)
                .into(photo)
            //photo.setImageResource(infoList[i - 1])

            layout_content?.addView(photo, params)
            layout_content?.addView(line, line_params)
        }

        val question_text = TextView(this.context)
        question_text.text = question
        layout_content?.addView(question_text)

        val question = RadioGroup(this.context)
        for (i in 1..answers.size) {
            val option = RadioButton(this.context)
            option.text = answers[i - 1]
            option.id = i - 1
            question.addView(option, answer_params)
        }


        val verification = Button(this.context)
        verification.text = "Verificar"

        verification.setOnClickListener {
            val checkId = question.checkedRadioButtonId
            if (checkId === 0) {
                verification.setBackgroundColor(Color.GREEN)
            } else {
                    verification.setBackgroundColor(Color.RED)
            }
        }

        layout_content?.addView(question, question_params)
        layout_content?.addView(verification)
    }

}
