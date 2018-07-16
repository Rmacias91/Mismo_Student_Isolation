package com.example.richardmacias.cs6460.data.firebase

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.richardmacias.cs6460.data.Repository
import com.example.richardmacias.cs6460.features.ContentList.models.ContentCard
import com.example.richardmacias.cs6460.features.MainMeetList.models.MeetCard
import com.google.firebase.database.*

class OnlineDatabase{

    private var mDatabase: DatabaseReference? = null
    private var mMeetsReference: DatabaseReference? = null
    private var meetsLive: MutableLiveData<Array<MeetCard>>

    constructor(){
        meetsLive = MutableLiveData()
    }

    fun initDatabase() {
        setReference()
    }

    fun updateMeet(meetCard: MeetCard){
        mDatabase!!.child("meets").child(meetCard.onlineId).setValue(meetCard)
    }

      fun getMeets(listener: Repository.listListener){
        val meetListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
               val meets:List<MeetCard> =  dataSnapshot.children.mapNotNull { it.getValue<MeetCard>(MeetCard::class.java) }
                Log.d("richie",meets.size.toString())
                listener.onDataLoaded(meets)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadPost:onCancelled ${databaseError.toException()}")
            }
        }
        mDatabase!!.child("meets").addListenerForSingleValueEvent(meetListener)
    }

    fun getMeet(listener: Repository.itemListener,id:String){
        val meetListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
               val meetCard:MeetCard? = dataSnapshot.child(id).getValue(MeetCard::class.java)
                Log.d("richie",meetCard!!.title)
                listener.onDataLoaded(meetCard)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadPost:onCancelled ${databaseError.toException()}")
            }
        }
        mDatabase!!.child("meets").addListenerForSingleValueEvent(meetListener)
    }

    fun addMeet(meetCard: MeetCard){
        val key = mDatabase!!.child("meets").push().key
        meetCard.onlineId = key
        mDatabase!!.child("meets").child(key).setValue(meetCard)
    }

    fun addContentVideos(videoContent:ContentCard){
        val key = mDatabase!!.child("content").push().key
        videoContent.onlineId = key
        mDatabase!!.child("content").child(key).setValue(videoContent)
    }

    fun getDetailContent(listener: Repository.contentDetailListener,id:String){
        val contentDetailListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val contentCard:ContentCard? = dataSnapshot.child(id).getValue(ContentCard::class.java)
                Log.d("richie",contentCard!!.title)
                listener.onDataLoaded(contentCard);
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadPost:onCancelled ${databaseError.toException()}")
            }
        }
        mDatabase!!.child("content").addListenerForSingleValueEvent(contentDetailListener)
    }

    fun getContent(listener: Repository.contentListListener){
        val contentListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val content:List<ContentCard> =  dataSnapshot.children.mapNotNull { it.getValue<ContentCard>(ContentCard::class.java) }
                Log.d("richie",content.size.toString())
                listener.onDataLoaded(content)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadPost:onCancelled ${databaseError.toException()}")
            }
        }
        mDatabase!!.child("content").addListenerForSingleValueEvent(contentListener)
    }



    private fun setReference(){
        mDatabase = FirebaseDatabase.getInstance().reference
        mMeetsReference = FirebaseDatabase.getInstance().getReference("meets")
    }
}