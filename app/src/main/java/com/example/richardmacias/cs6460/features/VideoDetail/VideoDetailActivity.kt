package com.example.richardmacias.cs6460.features.VideoDetail

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.View
import com.example.richardmacias.cs6460.Config
import com.example.richardmacias.cs6460.R
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import android.widget.Toast

class VideoDetailActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private val RECOVERY_REQUEST = 1
    private var youTubeView: YouTubePlayerView? = null
    private var link:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.video_content_layout)
        link = intent.getStringExtra("link")
        youTubeView = findViewById(R.id.youtube_view)
        youTubeView!!.initialize(Config.YOUTUBE_API_KEY, this)
        var navbar: BottomNavigationView = findViewById(R.id.bottom_nav_bar)
        navbar.visibility = View.GONE
    }


    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean) {
        if (!wasRestored) {
            player?.cueVideo(link)
        }
    }

        override fun onInitializationFailure(p0: YouTubePlayer.Provider?, errorReason: YouTubeInitializationResult?) {
            if (errorReason!!.isUserRecoverableError()) {
                errorReason.getErrorDialog(this, RECOVERY_REQUEST).show()
            } else {
                val error = String.format(getString(R.string.player_error), errorReason.toString())
                Toast.makeText(this, error, Toast.LENGTH_LONG).show()
            }
        }








}