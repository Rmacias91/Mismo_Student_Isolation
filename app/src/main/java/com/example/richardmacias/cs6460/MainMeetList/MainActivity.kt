package com.example.richardmacias.cs6460.MainMeetList

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.richardmacias.cs6460.Data.Card
import com.example.richardmacias.cs6460.R

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val myList:MutableList<Card> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createDummyData()

        viewManager = LinearLayoutManager(this)
        viewAdapter = CustomAdapter(myList)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }

    }


    private fun createDummyData(){
        val card1 = Card("Zoo After school","After school   Outdoors",
                "We're off to the Zoo after school. Anyone is welcome to join!")
        val card2 = Card("We're playing chess at lunch!","Lunch Chess Friends",
                "Anyone can sit with us. No experience needed.")

        myList.add(card1)
        myList.add(card2)

    }



}
