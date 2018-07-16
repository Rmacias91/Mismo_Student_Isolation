package com.example.richardmacias.cs6460.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.example.richardmacias.cs6460.Utils.SingletonHolder
import com.example.richardmacias.cs6460.data.firebase.OnlineDatabase
import com.example.richardmacias.cs6460.features.ContentList.models.ContentCard
import com.example.richardmacias.cs6460.features.MainMeetList.models.MeetCard


class Repository private constructor()//private val meetCardDao: MeetCardDao,
                                     //private val contentCardDao: ContentCardDao,
                                     //private val fireBaseDataSource: OnlineDatabase,
                                     //private val mExecutors: AppExecutors) {
{
    interface listListener{
        fun onDataLoaded(meets:List<MeetCard>)
    }

    interface contentListListener {
        fun onDataLoaded(content:List<ContentCard>)
    }

    interface itemListener{
        fun onDataLoaded(meetCard: MeetCard)
    }

    interface contentDetailListener{
        fun onDataLoaded(contentCard: ContentCard)
    }



    companion object {
        private var sInstance: Repository? = null
        private val LOCK = Any()

        @Synchronized
        fun getInstance(): Repository? {
            if (sInstance == null) {
                synchronized(LOCK) {
                    sInstance = Repository()
                }
            }
            return sInstance
        }
    }




    val firebaseDataSource:OnlineDatabase = OnlineDatabase()
    //var liveMeets:LiveData<Array<MeetCard>> = MutableLiveData()

//    fun getMeets():LiveData<Array<MeetCard>>{
//        return liveMeets
//    }

    fun initDB(){
        firebaseDataSource.initDatabase()
    }

    fun getMeets(listener: listListener){
        firebaseDataSource.getMeets(listener)
    }

    fun getMeet(listener: itemListener, id:String){
        firebaseDataSource.getMeet(listener,id)
    }

    fun updateMeet(meetCard: MeetCard){
        firebaseDataSource.updateMeet(meetCard)
    }

    fun addMeet(meetCard:MeetCard){
        firebaseDataSource.addMeet(meetCard)
    }

    fun getContent(listener: contentListListener){
        firebaseDataSource.getContent(listener)
    }

    fun getDetailContent(listener: contentDetailListener,id:String){
        firebaseDataSource.getDetailContent(listener,id)
    }

    fun createVideos(content: List<ContentCard>){
        for(video in content)
        firebaseDataSource.addContentVideos(video)
    }
}