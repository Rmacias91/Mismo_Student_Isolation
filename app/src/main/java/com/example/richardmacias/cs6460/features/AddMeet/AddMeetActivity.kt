package com.example.richardmacias.cs6460.features.AddMeet

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.example.richardmacias.cs6460.R

class AddMeetActivity : AppCompatActivity() {

    private lateinit var editTitle: EditText
    private lateinit var buttonDate: Button
    private lateinit var spinnerCategory: Spinner
    private lateinit var editDetails:EditText
    private lateinit var buttonSave:Button
    private lateinit var toolbar:ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_meet_layout)

    }
}