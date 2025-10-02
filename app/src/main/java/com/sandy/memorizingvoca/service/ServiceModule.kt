package com.sandy.memorizingvoca.service

import android.content.ComponentName
import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaController
import androidx.media3.session.MediaSession
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun providesExoPlayer(
        @ApplicationContext context: Context,
    ): ExoPlayer {
        return ExoPlayer.Builder(context).build()
    }

    @Provides
    @Singleton
    fun providesMediaSession(
        @ApplicationContext context: Context,
        player: ExoPlayer,
    ): MediaSession {
        return MediaSession.Builder(context, player).build()
    }

    @Provides
    @Singleton
    fun provideSessionToken(
        @ApplicationContext context: Context,
    ): SessionToken {
        val serviceComponent = ComponentName(context, MediaPlaybackService::class.java)
        return SessionToken(context, serviceComponent)
    }


    @Provides
    @Singleton
    fun provideMediaControllerFuture(
        @ApplicationContext context: Context,
        serviceToken: SessionToken,
    ): ListenableFuture<MediaController> {
        return MediaController.Builder(context, serviceToken).buildAsync()
    }

}