package com.example.richardmacias.cs6460.features.AddMeet

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.example.richardmacias.cs6460.R
import com.example.richardmacias.cs6460.core.BaseActivity
import com.example.richardmacias.cs6460.data.Repository
import com.example.richardmacias.cs6460.features.MainMeetList.models.MeetCard
import java.text.SimpleDateFormat
import java.util.*


class AddMeetActivity : BaseActivity() {

    private lateinit var editTitle: EditText
    private lateinit var buttonDate: Button
    private lateinit var spinnerCategory: Spinner
    private lateinit var editDetails:EditText
    private lateinit var editLocation:EditText
    private lateinit var buttonSave:Button
    private var toolbar:ActionBar? = null
    private var category:String = "Board Games"
    private var repository:Repository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_meet_layout)
        findViews()
        setViews()
        repository = Repository.getInstance()
    }




    private fun findViews(){
        editTitle = findViewById(R.id.edit_title_add)
        buttonDate = findViewById(R.id.button_date_add)
        spinnerCategory = findViewById(R.id.spinner_category_add)
        buttonSave = findViewById(R.id.button_save_add)
        editDetails = findViewById(R.id.edit_details_add)
        editLocation = findViewById(R.id.edit_where_add)
        toolbar = supportActionBar


    }

    private fun setViews(){
        buttonSave.setOnClickListener{save()}
        setUpNavBar()
        val adapter = ArrayAdapter.createFromResource(this, R.array.categories,android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = adapter

        spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                category = parent.getItemAtPosition(position).toString()

            } // to close the onItemSelected

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }


        val cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd/MM/yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            buttonDate.text = sdf.format(cal.time)
        }

        buttonDate.setOnClickListener {
            DatePickerDialog(this, dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun save(){
        if(editTitle.text.isNotEmpty() && buttonDate.text.isNotEmpty() && category.isNotEmpty() &&
                editLocation.text.isNotEmpty()){

             val meetCard = MeetCard(editTitle.text.toString(),
                     category, editDetails.text.toString(),
                     buttonDate.text.toString(), editLocation.text.toString(),1  )

            repository?.addMeet(meetCard)
            Toast.makeText(this,"Saved!",Toast.LENGTH_SHORT).show()
            finish()

        }else
            Toast.makeText(this,"Please fill in a complete event",Toast.LENGTH_SHORT).show()
    }




}