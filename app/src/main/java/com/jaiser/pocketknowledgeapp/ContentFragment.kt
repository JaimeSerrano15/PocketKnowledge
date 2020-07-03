package com.jaiser.pocketknowledgeapp

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.jaiser.pocketknowledgeapp.databinding.FragmentContentBinding
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass.
 */
class ContentFragment : Fragment() {

    //val infoList = mutableListOf(R.drawable.soc1, R.drawable.soc2)

    lateinit var lesson_name : String
    lateinit var level : String
    lateinit var lessonid : String

    private var imagesList = ArrayList<String>()
    private var question = String()
    private var answers = arrayListOf<String>()
    private val db = FirebaseFirestore.getInstance()
    private var nice = String()
    private lateinit var auth : FirebaseAuth;
    private lateinit var userID : String
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

        lesson_name = args.lessionTittle
        level = args.level
        lessonid = args.lessonId.toString()

        auth = FirebaseAuth.getInstance()

        showContent(binding, args)


        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.fav_menu, menu)

        var menu : MenuItem = menu.getItem(0)
        userID = auth.currentUser!!.uid

        CheckFavState(lesson_name, menu)
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

    fun CheckFavState (lessonName: String, menu : MenuItem) {
        var currentUser = db.collection("users").document(userID)

        currentUser.get().addOnSuccessListener{document->
            var favorites = document.get("fav") as ArrayList<HashMap<String, String>>

            for(h in favorites){
                if(h["lesson"] == lessonName){
                    favMark = true
                }
                if(favMark)  menu.icon = ContextCompat.getDrawable(this.context!!, R.drawable.ic_favorite_black_24dp)
                else menu.icon = ContextCompat.getDrawable(this.context!!, R.drawable.ic_favorite_border_black_24dp)
                }

            }
        }


    fun changeFavoriteIcon(menuItem: MenuItem){
        var id = if(favMark){
            AddFav(lesson_name, level, lessonid)
            R.drawable.ic_favorite_black_24dp
        }
        else{
          DeleteFav(lesson_name)
            R.drawable.ic_favorite_border_black_24dp
        }
        menuItem.icon = ContextCompat.getDrawable(this.context!!, id)

    }

    fun AddFav( lessonName: String, level: String, id : String){
         var currentUser = db.collection("users").document(userID)

        currentUser.get().addOnSuccessListener{document->
            var favorites = document.get("fav") as ArrayList<Any>

            var fav = mapOf<String, String>("nivel" to level, "lesson" to lessonName, "id" to id)
                Log.d("TAG", "nivel: ${fav["nivel"]} leccion ${fav["lesson"]}")

            favorites.add(fav)
            var documentReference : DocumentReference = db.collection("users").document(document.id)
            documentReference.update("fav", favorites)
        }

    }

    fun DeleteFav (lessonName: String){
        var currentUser = db.collection("users").document(userID)

        currentUser.get().addOnSuccessListener{document->
            var favorites = document.get("fav") as ArrayList<HashMap<String, String>>
            for(h in favorites) {
                if (h["lesson"] == lessonName) {
                    Log.d("TAG", "Se va a remover ${h["lesson"]} por que es igual a ${lessonName}")
                    favorites.remove(h)
                }
            }
            var documentReference : DocumentReference = db.collection("users").document(document.id)
            documentReference.update("fav", favorites)
        }
    }



    fun setup(lesson_title: String) {
        (activity as AppCompatActivity).supportActionBar?.title = lesson_title
    }

    fun showContent(binding: FragmentContentBinding, args: ContentFragmentArgs) {
        getData(args, binding)
    }

    private fun getData(args: ContentFragmentArgs, binding: FragmentContentBinding) {
        val docRef = db.collection(args.level).document(args.lessonId.toString())
        docRef.get().addOnSuccessListener { document ->
            if (document != null) {
                Toast.makeText(this.context, "IMAGENES CARGADAS", Toast.LENGTH_SHORT).show()
                imagesList = document.get("images") as ArrayList<String>
                question = document.get("question").toString()
                answers = document.get("ans") as ArrayList<String>
                nice = document.get("nice").toString()
                setUpLesson(binding)
            } else {
                Log.i("info", "No such document")
            }
        }
            .addOnFailureListener { exception ->
                Log.i("info", "get failed with ", exception)
            }
    }

    private fun setUpLesson( binding: FragmentContentBinding) {
        val index = imagesList?.size
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
            LinearLayout.LayoutParams.MATCH_PARENT,
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
            if(answers[i-1] == nice){
                option.id = (i*0) + 7
            }
            question.addView(option, answer_params)
        }


        val verification = Button(this.context)
        verification.text = "Verificar"
        verification.setBackgroundResource(R.drawable.rounded_button)

        verification.setOnClickListener {
            val checkId = question.checkedRadioButtonId
            if (checkId === 7) {
                verification.setBackgroundResource(R.drawable.rounden_button_green)
                Toast.makeText(this.context, "Respuesta correcta!!!", Toast.LENGTH_SHORT).show()
                question.clearCheck()
            } else {
                verification.setBackgroundResource(R.drawable.rounded_button_red)
                Toast.makeText(this.context, "Respuesta incorrecta:(, vuelve a intentar", Toast.LENGTH_SHORT).show()
                question.clearCheck()
            }
        }

        layout_content?.addView(question, question_params)
        layout_content?.addView(verification)
    }


}
