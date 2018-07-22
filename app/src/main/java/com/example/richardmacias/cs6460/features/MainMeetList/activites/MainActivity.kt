package com.example.richardmacias.cs6460.features.MainMeetList.activites

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import com.example.richardmacias.cs6460.features.MainMeetList.models.MeetCard
import com.example.richardmacias.cs6460.R
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.ActionBar
import android.support.v7.widget.SearchView
import android.view.View
import android.widget.ProgressBar
import com.example.richardmacias.cs6460.Constants.Constants
import com.example.richardmacias.cs6460.core.BaseActivity
import com.example.richardmacias.cs6460.data.Repository
import com.example.richardmacias.cs6460.features.AddMeet.AddMeetActivity
import com.example.richardmacias.cs6460.features.DetailMeet.DetailMeetActivity
import com.example.richardmacias.cs6460.features.MainMeetList.adapters.MeetAdapter
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : BaseActivity(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var toolbar: ActionBar
    private lateinit var fabAddMeet: FloatingActionButton
    private lateinit var progressBar: ProgressBar
    private var showSearchHint:Boolean = true
    private  var query:String = ""
    private val repository:Repository? = Repository.getInstance()

    private val myList:MutableList<MeetCard> = mutableListOf()
    private val fullList:MutableList<MeetCard> = mutableListOf()

    private val itemClickListener = object: MeetAdapter.onItemClickListener {
        override fun itemClick(position:Int) {
            goToDetailActivity(position)
        }
    }

    private val onDataLoaded = object:Repository.listListener{
        override fun onDataLoaded(meets:List<MeetCard>) {
            fullList.addAll(meets)
            deleteOldMeets()
            fullList.sortBy { meetCard ->
                SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(meetCard.date)
            }
            progressBar.visibility = View.GONE
            myList.clear()
            myList.addAll(fullList)
            viewAdapter.notifyDataSetChanged()
            if (query.isNotEmpty())
                doSearch(query)
        }
    }
    //TODO MATCH MEETUPS TO CONTENT VIDEO/ARTICLES


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewManager = LinearLayoutManager(this)
        viewAdapter = MeetAdapter(myList, itemClickListener)
        toolbar = supportActionBar!!
        findViews()
        setViews()
        val intent: Intent = intent
        if (Intent.ACTION_SEARCH == (intent.action)) {
            query = intent.getStringExtra(SearchManager.QUERY)

        }
        repository!!.initDB()
        repository.getMeets(onDataLoaded)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.menu_search)?.actionView as SearchView
        when(showSearchHint){
            true -> searchView.queryHint = getString(R.string.search_hint)
            false -> searchView.queryHint = getString(R.string.clear_search_hint)
        }

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(componentName))
        searchView.setOnCloseListener({
            resetSearch()
            false
        })
        return super.onCreateOptionsMenu(menu)
    }


    private fun findViews(){
        fabAddMeet = findViewById(R.id.fab)
        progressBar = findViewById(R.id.progress_bar_main)
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

    }

    private fun setViews(){
        fabAddMeet.setOnClickListener{goToAddActivity()}
        toolbar.title = "Who wants to hang out?"
        progressBar.visibility = View.VISIBLE
        setUpNavBar()

    }

    private fun goToAddActivity(){
        val intent = Intent(this, AddMeetActivity::class.java)
        startActivity(intent)
    }

    private fun goToDetailActivity(position:Int){
        val uuid:String = myList.get(position).onlineId
        val intent = Intent(this, DetailMeetActivity::class.java)
        intent.putExtra(Constants.EXTRA_DETAIL_MEET,uuid)
        startActivity(intent)
    }


    private fun createDummyData(){
        val card1 = MeetCard("Zoo After school", Constants.CATEGORY_AFTER_SCHOOL,
                "We're off to the Zoo after school. Anyone is welcome to join!","stuff","stuff",4)
        val card2 = MeetCard("We're playing chess at lunch!", "Lunch Chess Friends",
                "Anyone can sit with us. No experience needed.","","",4)


        myList.add(card1)
        myList.add(card2)
        myList.add(card2)
        fullList.addAll(myList)
    }

    private fun doSearch(query:String){
        val lowerCaseQuery:String = query.toLowerCase()
        val searchResults:MutableList<MeetCard> = mutableListOf()
        for(meetCard in myList){
            if(meetCard.category.toLowerCase().contains(lowerCaseQuery) ||
                    meetCard.title.toLowerCase().contains(lowerCaseQuery) ||
                    meetCard.description.toLowerCase().contains(lowerCaseQuery))
                searchResults.add(meetCard)
        }
        showSearchHint = false
        myList.clear()
        myList.addAll(searchResults)
        viewAdapter.notifyDataSetChanged()

    }

    private fun resetSearch(){
            showSearchHint = true
            myList.clear()
            myList.addAll(fullList)
            viewAdapter.notifyDataSetChanged()
    }

    private fun deleteOldMeets() {
        val oldMeets: MutableList<MeetCard> = mutableListOf()
        for (meet in fullList) {
            val meetDate = SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(meet.date)
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            var todayDate = Date()
            val todayString = dateFormat.format(todayDate)
            todayDate = SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(todayString)
            if (meetDate < todayDate) {
                oldMeets.add(meet)
            }
        }
        if (oldMeets.size > 0) {
            repository?.deleteOldMeets(oldMeets)
            fullList.removeAll(oldMeets)
        }
    }






}
