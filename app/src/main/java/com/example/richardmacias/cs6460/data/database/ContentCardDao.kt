package com.example.richardmacias.cs6460.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.richardmacias.cs6460.features.ContentList.models.ContentCard

@Dao
interface ContentCardDao {

    @Query("SELECT * FROM contents")
    fun getAllContent(): List<ContentCard>

    @Query("SELECT * FROM meets where id = :id")
    fun get(id:Long): ContentCard

    @Insert
    fun insert(contentCard: ContentCard)

    @Query("DELETE FROM contents WHERE id = :id")
    fun delete(id:Long)


    @Query("UPDATE contents SET title=:title, category = :category , description = :description, isArticle = :isArticle")
    fun update(title:String,category: String, description:String, isArticle:Boolean)
}