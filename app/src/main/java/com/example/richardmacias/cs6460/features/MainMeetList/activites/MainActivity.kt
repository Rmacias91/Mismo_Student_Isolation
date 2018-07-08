package com.example.richardmacias.cs6460.features.MainMeetList.activites

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import com.example.richardmacias.cs6460.features.MainMeetList.models.MeetCard
import com.example.richardmacias.cs6460.R
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.ActionBar
import android.support.v7.widget.SearchView
import android.view.MenuItem
import com.example.richardmacias.cs6460.Constants.Constants
import com.example.richardmacias.cs6460.data.Repository
import com.example.richardmacias.cs6460.features.AddMeet.AddMeetActivity
import com.example.richardmacias.cs6460.features.ContentList.activites.ContentListActivity
import com.example.richardmacias.cs6460.features.DetailMeet.DetailMeetActivity
import com.example.richardmacias.cs6460.features.MainMeetList.adapters.MeetAdapter


class MainActivity : AppCompatActivity(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var toolbar: ActionBar
    private lateinit var fabAddMeet: FloatingActionButton
    private lateinit var bottomNavigation: BottomNavigationView

    private val myList:MutableList<MeetCard> = mutableListOf()

    private val itemClickListener = object: MeetAdapter.onItemClickListener {
        override fun itemClick(position:Int) {
            goToDetailActivity(position)
        }
    }

    private val onDataLoaded = object:Repository.listListener{
        override fun onDataLoaded(meets:List<MeetCard>) {
            myList.clear()
            myList.addAll(meets)
            viewAdapter.notifyDataSetChanged()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createDummyData()

        viewManager = LinearLayoutManager(this)
        viewAdapter = MeetAdapter(myList, itemClickListener)
        toolbar = supportActionBar!!
        findViews()
        setViews()
        val repository = Repository()
        repository.initDB()
        repository.getMeets(onDataLoaded)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView:SearchView = menu?.findItem(R.id.menu_search)?.actionView as SearchView
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(componentName))
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.navigation_meet->{}
            R.id.navigation_learn->goToContentList()

        }
        return super.onOptionsItemSelected(item)
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
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

    private fun goToAddActivity(){
        val intent = Intent(this, AddMeetActivity::class.java)
        startActivity(intent)
    }

    private fun goToDetailActivity(position:Int){
        val intent = Intent(this, DetailMeetActivity::class.java)
        startActivity(intent)
    }

    private fun goToContentList(){
        val intent = Intent(this, ContentListActivity::class.java)
        startActivity(intent)
    }

    private fun goToMeetList(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }



    private fun createDummyData(){
        val card1 = MeetCard("Zoo After school", "After school   Outdoors",
                "We're off to the Zoo after school. Anyone is welcome to join!","stuff","stuff",4)
        val card2 = MeetCard("We're playing chess at lunch!", "Lunch Chess Friends",
                "Anyone can sit with us. No experience needed.","","",4)


        myList.add(card1)
//        myList.add(card2)
//        myList.add(card2)
//        myList.add(card2)

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_meet -> {
                if(this.javaClass.simpleName != Constants.MAIN_ACTIVITY)
                    goToMeetList()

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_learn -> {
                if(this.javaClass.simpleName != Constants.CONTENT_LIST_ACTIVITY)
                    goToContentList()

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }



}
