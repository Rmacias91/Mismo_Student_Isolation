package com.example.richardmacias.cs6460.features.DetailMeet

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.richardmacias.cs6460.R
import android.arch.lifecycle.ViewModelProviders
import android.widget.Button
import android.widget.TextView
import com.example.richardmacias.cs6460.features.MainMeetList.models.MeetCard


class DetailMeetActivity : AppCompatActivity(){

    private lateinit var viewModel:DetailMeetViewModel
    private lateinit var titleText:TextView
    private lateinit var whenText:TextView
    private lateinit var whereText:TextView
    private lateinit var whoText:TextView
    private lateinit var additonalInfoText:TextView
    private lateinit var joinButton:Button

    private var counter:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.meet_detail_layout)
        findViews()

        //This returns a view model and will not re-create if on already exists.
        viewModel = ViewModelProviders.of(this).get(DetailMeetViewModel::class.java)
        viewModel.getContent().observe(this, Observer<MeetCard> { meetCard ->
            if (meetCard != null) bindMeetCardToUI(meetCard)
        })
    }

    private fun findViews(){
        titleText = findViewById(R.id.text_title_detail)
        whenText = findViewById(R.id.text_when_detail)
        whereText = findViewById(R.id.text_where_detail)
        whoText = findViewById(R.id.text_who_detail)
        additonalInfoText = findViewById(R.id.text_add_info_detail)
        joinButton = findViewById(R.id.button_join_detail)
        joinButton.setOnClickListener{createNewCard()}
    }

    private fun bindMeetCardToUI(meetCard:MeetCard){
        titleText.text = meetCard.title
        whenText.text = meetCard.date
        whereText.text = meetCard.location
        whoText.text = meetCard.numberGoing.toString()
        additonalInfoText.text = meetCard.description
    }

    private fun createNewCard(){
        counter++
        val card = MeetCard("We're playing chess at lunch!", "Lunch Chess Friends",
                "Anyone can sit with us. No experience needed.","","",counter)
        viewModel.setContent(card)
    }
}