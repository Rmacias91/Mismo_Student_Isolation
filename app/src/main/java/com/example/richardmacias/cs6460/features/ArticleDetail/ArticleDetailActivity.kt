package com.example.richardmacias.cs6460.features.ArticleDetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView



class ArticleDetailActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val webView = WebView(this)
        setContentView(webView)
        val link:String = intent.getStringExtra("link")
        webView.loadUrl(link)
    }
}