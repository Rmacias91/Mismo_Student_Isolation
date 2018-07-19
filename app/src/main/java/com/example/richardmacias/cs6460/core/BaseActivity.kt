package com.example.richardmacias.cs6460.core

import android.content.Intent
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import com.example.richardmacias.cs6460.Constants.Constants
import com.example.richardmacias.cs6460.R
import com.example.richardmacias.cs6460.features.ContentList.activites.ContentListActivity
import com.example.richardmacias.cs6460.features.MainMeetList.activites.MainActivity

open class BaseActivity:AppCompatActivity(){

    private lateinit var bottomNavigation: BottomNavigationView

    private fun goToContentList(){
        val intent = Intent(this, ContentListActivity::class.java)
        startActivity(intent)
    }

    private fun goToMeetList(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun setUpNavBar(){
        if(findViewById<NavigationView>(R.id.navigation_view)!= null){
            bottomNavigation = findViewById(R.id.navigation_view)
            bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        }
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