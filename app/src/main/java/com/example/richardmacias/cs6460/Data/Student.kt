package com.example.richardmacias.cs6460.Data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "student")
class Student {

    @PrimaryKey(autoGenerate = true)
    private var studentID: Int = 0
}