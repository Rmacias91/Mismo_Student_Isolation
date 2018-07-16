package com.example.richardmacias.cs6460.features.ContentList.activites

import android.content.Intent
import android.opengl.Visibility
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
import com.example.richardmacias.cs6460.data.Repository
import com.example.richardmacias.cs6460.features.ArticleDetail.ArticleDetailActivity
import com.example.richardmacias.cs6460.features.ContentList.adapters.ContentAdapter
import com.example.richardmacias.cs6460.features.ContentList.models.ContentCard
import com.example.richardmacias.cs6460.features.MainMeetList.activites.MainActivity
import com.example.richardmacias.cs6460.features.MainMeetList.models.MeetCard
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
    private lateinit var fabAddVideo: FloatingActionButton
    private lateinit var bottomNavigation: BottomNavigationView

    private val myList:MutableList<ContentCard> = mutableListOf()


    private val onDataLoaded = object:Repository.contentListListener{
        override fun onDataLoaded(content:List<ContentCard>) {
            myList.clear()
            myList.addAll(content)
            viewAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = ContentAdapter(myList,itemClickListener)
        toolbar = supportActionBar!!
        findViews()
        setViews()
        val repository = Repository.getInstance()
        repository!!.initDB()
        repository.getContent(onDataLoaded)

    }

    private fun findViews(){
        bottomNavigation = findViewById(R.id.navigation_view)
        fabAddVideo = findViewById(R.id.fab)
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun setViews(){
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        fabAddVideo.visibility = View.GONE

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
    private fun goToVideo(postion: Int){
        val intent = Intent(this, VideoDetailActivity::class.java)
        intent.putExtra("link",myList.get(postion).link)
        startActivity(intent)
    }



    private fun goToContentDetail(postion:Int, isArticle:Boolean){
        if(isArticle)
            goToArticle()
        else
            goToVideo(postion)
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

    private fun createVideoCards(repository: Repository){

        var videos:ArrayList<ContentCard> = arrayListOf()


        val video1 = ContentCard("Improve Your Social Skills in Under 30 Minutes, with Ramit Sethi",
                "Social Skills","",false, "1eOKwU0LLTE");

        val video2 = ContentCard("Think Fast, Talk Smart: Communication Techniques",
                "Communication","Stanford Graduate School of Business",
                false,link="HAnw168huqA")

        val video3 = ContentCard("Conversation Hacking: How To Instantly Upgrade Your Conversation Skills",
                "Communication","21 Studios",
                false,link="uYuACPtiOjU")

        val video4 = ContentCard("Body Language Expert Keynote Mark Bowden at TEDx Toronto — " +
                "The Importance Of Being Inauthentic",
                "Communication","Mark Bowden",
                false,link="rk_SMBIW1mg")

        val video5 = ContentCard("The skill of self confidence ",
                "Confidence","Dr. Ivan Joseph TedX Talks",
                false,link="w-HYZv6HzAs")

        val video6 = ContentCard("10 ways to have a better conversation",
                "Communication","Celeste Headlee Ted",
                false,link="R1vskiVDwl4")

        val video7 = ContentCard("10 ways to have a better conversation",
                "Confidence","Celeste Headlee Ted",
                false,link="R1vskiVDwl4")

        val video8 = ContentCard("How to engage in better small talk",
                "Communication","Minister Faust TedX Talks",
                false,link="4F-S6rgf1-E")

        videos.add(video1)
        videos.add(video2)
        videos.add(video3)
        videos.add(video4)
        videos.add(video5)
        videos.add(video6)
        videos.add(video7)
        videos.add(video8)

        repository.createVideos(videos)

    }
}