package com.example.richardmacias.cs6460.features.DetailMeet

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.richardmacias.cs6460.features.MainMeetList.models.MeetCard

class DetailMeetViewModel:ViewModel(){

    private var meetCard:MutableLiveData<MeetCard> = MutableLiveData()

    fun getContent():LiveData<MeetCard>{
        return  meetCard
    }

    fun setContent(meetCard:MeetCard){
       this.meetCard.postValue(meetCard)
    }
}