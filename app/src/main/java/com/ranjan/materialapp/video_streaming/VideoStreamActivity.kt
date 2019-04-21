package com.ranjan.materialapp.video_streaming

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayerFactory
import com.ranjan.materialapp.R


class VideoStreamActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_stream)

        val videoView = findViewById<VideoView>(R.id.video_stream_view)

        val vidAddress = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4"
        val vidUri = Uri.parse(vidAddress)

        videoView.setVideoURI(vidUri)

        videoView.start()

        val vidControl = MediaController(this)
        vidControl.setAnchorView(videoView)

        videoView.setMediaController(vidControl)

        val player = ExoPlayerFactory.newSimpleInstance(this)
    }
}
