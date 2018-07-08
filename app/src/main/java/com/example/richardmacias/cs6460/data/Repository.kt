package com.example.richardmacias.cs6460.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.richardmacias.cs6460.data.firebase.OnlineDatabase
import com.example.richardmacias.cs6460.features.MainMeetList.models.MeetCard


class Repository constructor(//private val meetCardDao: MeetCardDao,
                                     //private val contentCardDao: ContentCardDao,
                                     //private val fireBaseDataSource: OnlineDatabase,
                                     //private val mExecutors: AppExecutors) {
){
    interface listListener{
        fun onDataLoaded(meets:List<MeetCard>)
    }


    val firebaseDataSource:OnlineDatabase = OnlineDatabase()
    var liveMeets:LiveData<Array<MeetCard>> = MutableLiveData()

    fun getMeets():LiveData<Array<MeetCard>>{
        return liveMeets
    }

    fun initDB(){
        firebaseDataSource.initDatabase()
    }

    fun getMeets(listListener: listListener){
        firebaseDataSource.getMeets(listListener);
    }
}