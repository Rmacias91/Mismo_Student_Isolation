package com.example.richardmacias.cs6460.features.AddMeet

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.example.richardmacias.cs6460.R
import com.example.richardmacias.cs6460.features.MainMeetList.models.MeetCard
import com.example.richardmacias.cs6460.features.MainMeetList.models.MeetCardDao

class AddMeetActivity : AppCompatActivity() {

    private lateinit var editTitle: EditText
    private lateinit var buttonDate: Button
    private lateinit var spinnerCategory: Spinner
    private lateinit var editDetails:EditText
    private lateinit var buttonSave:Button
    private lateinit var toolbar:ActionBar
    private lateinit var meetDAO: MeetCardDao;
;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_meet_layout)
//        meetDAO = new MeetDAO()
        buttonSave = findViewById(R.id.button_save_add);
        buttonSave.setOnClickListener {
            System.out.println("Clicked save");

            var meet = buildMeetCard();
            meetDAO.insert();MeetCard
        };

    }

    private fun buildMeetCard(): MeetCard {

        var meetCard: MeetCard = MeetCard();
        meetCard.title =
        return meetCard;
    }


}