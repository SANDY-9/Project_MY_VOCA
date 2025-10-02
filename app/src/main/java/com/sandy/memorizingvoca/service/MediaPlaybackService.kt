package com.sandy.memorizingvoca.service

import android.content.Context
import android.content.Intent
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MediaPlaybackService : MediaSessionService() {

    private var mediaSession: MediaSession? = null
    // ExoPlayer를 lateinit var로 선언
    private lateinit var player: ExoPlayer

    override fun onCreate() {
        super.onCreate()
        player = ExoPlayer.Builder(this).build()
        mediaSession = MediaSession.Builder(this, player).build()

    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? {
        return mediaSession
    }

    override fun onDestroy() {
        mediaSession?.run {
            player.release()
            release()
            mediaSession = null
        }
        super.onDestroy()
    }

    companion object {
        fun destroyService(context: Context) {
            val intent = Intent(context, MediaPlaybackService::class.java)
            context.stopService(intent)
        }
    }

}