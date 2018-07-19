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
import com.example.richardmacias.cs6460.core.BaseActivity
import com.example.richardmacias.cs6460.data.Repository
import com.example.richardmacias.cs6460.features.ArticleDetail.ArticleDetailActivity
import com.example.richardmacias.cs6460.features.ContentList.adapters.ContentAdapter
import com.example.richardmacias.cs6460.features.ContentList.models.ContentCard
import com.example.richardmacias.cs6460.features.MainMeetList.activites.MainActivity
import com.example.richardmacias.cs6460.features.MainMeetList.models.MeetCard
import com.example.richardmacias.cs6460.features.VideoDetail.VideoDetailActivity

class ContentListActivity : BaseActivity() {

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
        fabAddVideo = findViewById(R.id.fab)
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun setViews(){
        setUpNavBar()
        fabAddVideo.visibility = View.GONE

    }

    private fun goToArticle(position:Int){
        val intent = Intent(this, ArticleDetailActivity::class.java)
        intent.putExtra("link",myList[position].link)
        startActivity(intent)
    }
    private fun goToVideo(postion: Int){
        val intent = Intent(this, VideoDetailActivity::class.java)
        intent.putExtra("link",myList[postion].link)
        startActivity(intent)
    }



    private fun goToContentDetail(postion:Int, isArticle:Boolean){
        if(isArticle)
            goToArticle(postion)
        else
            goToVideo(postion)
    }



    private fun createVideoCards(repository: Repository){

        var videos:ArrayList<ContentCard> = arrayListOf()


        val video1 = ContentCard("Improve Your Social Skills in Under 30 Minutes, with Ramit Sethi",
                "Social Skills","",false, "1eOKwU0LLTE");

        val video2 = ContentCard("Think Fast, Talk Smart: Communication Techniques",
                "Communication","Stanford Graduate School of Business",
                false,"HAnw168huqA")

        val video3 = ContentCard("Conversation Hacking: How To Instantly Upgrade Your Conversation Skills",
                "Communication","21 Studios",
                false,"uYuACPtiOjU")

        val video4 = ContentCard("Body Language Expert Keynote Mark Bowden at TEDx Toronto — " +
                "The Importance Of Being Inauthentic",
                "Communication","Mark Bowden",
                false,"rk_SMBIW1mg")

        val video5 = ContentCard("The skill of self confidence ",
                "Confidence","Dr. Ivan Joseph TedX Talks",
                false,"w-HYZv6HzAs")

        val video6 = ContentCard("10 ways to have a better conversation",
                "Communication","Celeste Headlee Ted",
                false,"R1vskiVDwl4")

        val video7 = ContentCard("10 ways to have a better conversation",
                "Confidence","Celeste Headlee Ted",
                false,"R1vskiVDwl4")

        val video8 = ContentCard("How to engage in better small talk",
                "Communication","Minister Faust TedX Talks",
                false,"4F-S6rgf1-E")

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

    fun createArticles(repository: Repository){
        var articles:ArrayList<ContentCard> = arrayListOf()
        val article1 = ContentCard("How to Make Friends And Get a Social Life",
                "Social Skills","A fairly common social issue people have is that they're not sure how to make friends and put together a social life for themselves.",
                true,"https://www.succeedsocially.com/sociallife")

        val article2 = ContentCard("How To Grow And Deepen New Friendships\n",
                "Social Skills","The concepts I'll describe below often happen automatically as a friendship progresses, but you can take more control of your social life by deliberately trying to use them.",
                true,"https://www.succeedsocially.com/deepenfriendship")

        val article3 = ContentCard("How To Meet People",
                "Social Skills","Sometimes people have lots of potential friends in their lives and they just need to do more to try to hang out with them and start a relationship.",
                true,"https://www.succeedsocially.com/meetpeople")

        val article4 = ContentCard("How To Find Events And Clubs In Your Community",
                "Social Skills","I find most communities have way more going on in them than you may think at first.",
                true,"https://www.succeedsocially.com/findinglocalevents")

        val article5 = ContentCard("How To Make Friends When You Have No Friends",
                "Social Skills","A group that feels like the process of building a social life is harder for them are people who don't have any friends at all. ",
                true,"https://www.succeedsocially.com/makingfriendsnofriends")

        val article6 = ContentCard("Changing Your Social Skills vs. Changing Your Entire Identity And Personality",
                "Social Skills","Some people aren't keen on the idea of having to changing how they come across socially.",
                true,"https://www.succeedsocially.com/skillsvspersonality")

        val article7 = ContentCard("Why Even Be More Social In The First Place?",
                "Social Skills","I'll cover the issue with a focus on the perspective of someone who's more skeptical about the point of being social.",
                true,"https://www.succeedsocially.com/whybesocial")

        val article8 = ContentCard("How To Join A Conversation",
                "Social Skills","Before trying to talk to a group of people you have to try to get a sense of how open or closed off they are.",
                true,"https://www.succeedsocially.com/joinconversations")
        articles.add(article1)
        articles.add(article2)
        articles.add(article3)
        articles.add(article4)
        articles.add(article5)
        articles.add(article6)
        articles.add(article7)
        articles.add(article8)

        repository.createVideos(articles)


    }
}