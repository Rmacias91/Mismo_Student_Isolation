package com.example.richardmacias.cs6460.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.richardmacias.cs6460.features.MainMeetList.models.MeetCard

@Dao
interface MeetCardDao {

    @Query("SELECT * FROM meets")
    fun getallMeets(): List<MeetCard>

    @Query("SELECT * FROM meets where id = :id")
    fun get(id:Long): MeetCard

    @Insert
    fun insert(meetCard: MeetCard)

    @Query("DELETE FROM meets WHERE id = :id")
    fun delete(id:Long)


    @Query("UPDATE meets SET title=:title, category = :category , description = :description")
    fun update(title:String,category: String, description:String)
}