package com.example.richardmacias.cs6460.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.example.richardmacias.cs6460.features.ContentList.models.ContentCard
import com.example.richardmacias.cs6460.features.MainMeetList.models.MeetCard
import android.content.Context
import com.example.richardmacias.cs6460.Constants.Constants
import com.example.richardmacias.cs6460.Utils.SingletonHolder


@Database(entities = [(MeetCard::class), (ContentCard::class)], version = 1)
abstract class MyDatabase: RoomDatabase() {

    abstract fun meetCardDao(): MeetCardDao
    abstract fun contentCardDao(): ContentCardDao

    companion object : SingletonHolder<MyDatabase, Context>({
        Room.databaseBuilder(it, MyDatabase::class.java, Constants.DATABASE_NAME).build()
    })


}

