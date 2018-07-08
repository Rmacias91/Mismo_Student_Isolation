package com.example.richardmacias.cs6460.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.example.richardmacias.cs6460.Utils.SingletonHolder
import com.example.richardmacias.cs6460.data.firebase.OnlineDatabase
import com.example.richardmacias.cs6460.features.MainMeetList.models.MeetCard


class Repository private constructor()//private val meetCardDao: MeetCardDao,
                                     //private val contentCardDao: ContentCardDao,
                                     //private val fireBaseDataSource: OnlineDatabase,
                                     //private val mExecutors: AppExecutors) {
{
    interface listListener{
        fun onDataLoaded(meets:List<MeetCard>)
    }

    interface itemListener{
        fun onDataLoaded(meetCard: MeetCard)
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
        firebaseDataSource.getMeets(listener);
    }

    fun getMeet(listener: itemListener, id:String){
        firebaseDataSource.getMeet(listener,id)
    }

    fun updateMeet(meetCard: MeetCard){
        firebaseDataSource.updateMeet(meetCard)
    }
}