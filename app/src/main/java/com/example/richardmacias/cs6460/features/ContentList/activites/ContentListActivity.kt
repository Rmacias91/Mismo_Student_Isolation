package com.example.richardmacias.cs6460.features.ContentList.activites

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.richardmacias.cs6460.Constants.Constants
import com.example.richardmacias.cs6460.R
import com.example.richardmacias.cs6460.features.ArticleDetail.ArticleDetailActivity
import com.example.richardmacias.cs6460.features.ContentList.adapters.ContentAdapter
import com.example.richardmacias.cs6460.features.ContentList.models.ContentCard
import com.example.richardmacias.cs6460.features.MainMeetList.activites.MainActivity
import com.example.richardmacias.cs6460.features.VideoDetail.VideoDetailActivity

class ContentListActivity : AppCompatActivity() {

    private val itemClickListener = object:ContentAdapter.onItemClickListener{
        override fun itemClick(position:Int, isArticle:Boolean) {
            goToContentDetail(position,isArticle)
        }
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var toolbar: ActionBar
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var fab:FloatingActionButton

    private val myList:MutableList<ContentCard> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        createDummyData()

        viewManager = LinearLayoutManager(this)
        viewAdapter = ContentAdapter(myList,itemClickListener)
        toolbar = supportActionBar!!
        findViews()
        setViews()

    }

    private fun findViews(){
        bottomNavigation = findViewById(R.id.navigation_view)
        fab = findViewById(R.id.fab)
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun setViews(){
        fab.visibility = View.GONE
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

    private fun goToContentList(){
        val intent = Intent(this, ContentListActivity::class.java)
        startActivity(intent)
    }

    private fun goToMeetList(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun goToArticle(){
        val intent = Intent(this, ArticleDetailActivity::class.java)
        startActivity(intent)
    }
    private fun goToVideo(){
        val intent = Intent(this, VideoDetailActivity::class.java)
        startActivity(intent)
    }



    private fun goToContentDetail(postion:Int, isArticle:Boolean){
        if(isArticle)
            goToArticle()
        else
            goToVideo()
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

    private fun createDummyData(){
        val card = ContentCard("Video on stuff","Video",
                "Check out this video on stuff",false)
        val card2 = ContentCard("Article on stuff","Article",
                "Check out this article on stuff",true)

        myList.add(card)
        myList.add(card2)
        myList.add(card)
        myList.add(card2)
    }
}