package com.example.richardmacias.cs6460.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.example.richardmacias.cs6460.features.ContentList.models.ContentCard
import com.example.richardmacias.cs6460.features.MainMeetList.models.MeetCard
import android.content.Context
import com.example.richardmacias.cs6460.Constants.Constants
import com.example.richardmacias.cs6460.Utils.SingletonHolder
import com.example.richardmacias.cs6460.features.ContentList.models.ContentCardDao
import com.example.richardmacias.cs6460.features.MainMeetList.models.MeetCardDao


@Database(entities = [(MeetCard::class), (ContentCard::class)], version = 1)
abstract class myDatabase: RoomDatabase() {

    abstract fun meetCardDao():MeetCardDao
    abstract fun contentCardDao():ContentCardDao

    companion object : SingletonHolder<myDatabase, Context>({
        Room.databaseBuilder(it, myDatabase::class.java, Constants.DATABASE_NAME).build()
    })


}

