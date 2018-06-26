package com.example.richardmacias.cs6460.MainMeetList

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import com.example.richardmacias.cs6460.Data.Card
import com.example.richardmacias.cs6460.R
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.ActionBar
import android.support.v7.widget.SearchView
import com.example.richardmacias.cs6460.AddMeet.AddMeetActivity
import com.example.richardmacias.cs6460.DetailMeetActivity.DetailMeetActivity


class MainActivity : AppCompatActivity(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var toolbar: ActionBar
    private lateinit var fabAddMeet: FloatingActionButton
    private lateinit var bottomNavigation: BottomNavigationView

    private val myList:MutableList<Card> = mutableListOf()

    private val itemClickListener = object:CustomAdapter.onItemClickListener{
        override fun itemClick(position:Int) {
            goToDetailActivity(position)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createDummyData()

        viewManager = LinearLayoutManager(this)
        viewAdapter = CustomAdapter(myList,itemClickListener)
        toolbar = supportActionBar!!
        findViews()
        setViews()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView:SearchView = menu?.findItem(R.id.menu_search)?.actionView as SearchView
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(componentName))
        return super.onCreateOptionsMenu(menu)
    }

    private fun findViews(){
        bottomNavigation = findViewById(R.id.navigation_view)
        fabAddMeet = findViewById(R.id.fab)
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

    }

    private fun setViews(){
        fabAddMeet.setOnClickListener{goToAddActivity()}


    }

    private fun goToAddActivity(){
        val intent = Intent(this, AddMeetActivity::class.java)
        startActivity(intent)
    }

    private fun goToDetailActivity(position:Int){
        val intent = Intent(this, DetailMeetActivity::class.java)
        startActivity(intent)
    }

    private fun createDummyData(){
        val card1 = Card("Zoo After school","After school   Outdoors",
                "We're off to the Zoo after school. Anyone is welcome to join!")
        val card2 = Card("We're playing chess at lunch!","Lunch Chess Friends",
                "Anyone can sit with us. No experience needed.")


        myList.add(card1)
        myList.add(card2)
        myList.add(card2)
        myList.add(card2)

    }



}
