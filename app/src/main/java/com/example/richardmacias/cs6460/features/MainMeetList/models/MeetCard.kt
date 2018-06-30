package com.example.richardmacias.cs6460.features.MainMeetList.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "meets")
class MeetCard( @PrimaryKey(autoGenerate = true) var id:Long?,
                var title:String,
                var category:String,
                var description:String,
                var date:String,
                var location:String,
                var numberGoing:Int){

constructor(title:String, category: String, description: String, date: String, location:String ,numberGoing: Int):
        this(null,title,category,description,date,location, numberGoing)

    constructor(): this(null, "", "", "", "", "", 0)

}

