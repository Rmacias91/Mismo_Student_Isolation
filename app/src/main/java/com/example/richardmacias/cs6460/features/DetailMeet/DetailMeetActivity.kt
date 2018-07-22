package com.example.richardmacias.cs6460.features.DetailMeet

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.richardmacias.cs6460.R
import android.arch.lifecycle.ViewModelProviders
import android.opengl.Visibility
import android.view.View
import android.widget.*
import com.example.richardmacias.cs6460.Constants.Constants
import com.example.richardmacias.cs6460.data.Repository
import com.example.richardmacias.cs6460.features.MainMeetList.models.MeetCard
import kotlinx.android.synthetic.main.card_layout.view.*


class DetailMeetActivity : AppCompatActivity(){

    private lateinit var viewModel:DetailMeetViewModel
    private lateinit var titleText:TextView
    private lateinit var whenText:TextView
    private lateinit var whereText:TextView
    private lateinit var whoText:TextView
    private lateinit var additonalInfoText:TextView
    private lateinit var joinButton:Button
    private lateinit var currentCard:MeetCard
    private lateinit var imageCover:ImageView
    private var repository:Repository? = null
    private var joined:Boolean = false
    private lateinit var progressBar: ProgressBar

    private val onDataLoaded = object:Repository.itemListener{
        override fun onDataLoaded(meetCard: MeetCard) {
            progressBar.visibility = View.GONE
            currentCard = meetCard
            bindMeetCardToUI(meetCard)

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.meet_detail_layout)
        val uuid:String = intent.getStringExtra(Constants.EXTRA_DETAIL_MEET)
        findViews()
        progressBar.visibility = View.VISIBLE
        repository = Repository.getInstance()
        repository!!.getMeet(onDataLoaded,uuid)

//        //This returns a view model and will not re-create if on already exists.
//        viewModel = ViewModelProviders.of(this).get(DetailMeetViewModel::class.java)
//        viewModel.getMeet().observe(this, Observer<MeetCard> { meetCard ->
//            if (meetCard != null) bindMeetCardToUI(meetCard)
//        })
    }

    private fun findViews(){
        titleText = findViewById(R.id.text_title_detail)
        whenText = findViewById(R.id.text_when_detail)
        whereText = findViewById(R.id.text_where_detail)
        whoText = findViewById(R.id.text_who_detail)
        additonalInfoText = findViewById(R.id.text_add_info_detail)
        joinButton = findViewById(R.id.button_join_detail)
        joinButton.setOnClickListener{join()}
        progressBar = findViewById(R.id.progress_bar_detail)
        imageCover = findViewById(R.id.image_cover_detail)
    }

    private fun bindMeetCardToUI(meetCard: MeetCard){
        titleText.text = meetCard.title
        whenText.text = meetCard.date
        whereText.text = meetCard.location
        whoText.text = meetCard.numberGoing.toString()
        additonalInfoText.text = meetCard.description
        val imageSrc = when (currentCard.category) {
            Constants.CATEGORY_AFTER_SCHOOL -> R.drawable.social
            Constants.CATEGORY_LUNCH -> R.drawable.lunch
            else -> R.drawable.social_tree
        }
        imageCover.setImageResource(imageSrc)
    }

    private fun join(){
        if(joined){
            Toast.makeText(this,"Already Joined!",Toast.LENGTH_SHORT).show()
        }else{
            joined = true
            currentCard.numberGoing = currentCard.numberGoing + 1
            repository!!.updateMeet(currentCard)
            whoText.text = currentCard.numberGoing.toString()
        }

        //viewModel.setMeet(card)
    }
}