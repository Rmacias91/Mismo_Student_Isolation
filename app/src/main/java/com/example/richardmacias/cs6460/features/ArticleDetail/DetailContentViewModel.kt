package com.example.richardmacias.cs6460.features.ArticleDetail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.richardmacias.cs6460.features.ContentList.models.ContentCard

class DetailContentViewModel: ViewModel(){

    private var contentCard:MutableLiveData<ContentCard> = MutableLiveData()

    fun getContent():LiveData<ContentCard>{
        return contentCard
    }

    fun setContent(contentCard:ContentCard){
        this.contentCard.postValue(contentCard)
    }
}