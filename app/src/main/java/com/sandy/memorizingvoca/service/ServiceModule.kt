package com.sandy.memorizingvoca.service

import android.content.ComponentName
import android.content.Context
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ServiceModule {

    @Provides
    @ViewModelScoped
    fun provideSessionToken(
        @ApplicationContext context: Context,
    ): SessionToken {
        val serviceComponent = ComponentName(context, MediaPlaybackService::class.java)
        return SessionToken(context, serviceComponent)
    }


    @Provides
    @ViewModelScoped
    fun provideMediaControllerFuture(
        @ApplicationContext context: Context,
        serviceToken: SessionToken,
    ): ListenableFuture<MediaController> {
        return MediaController.Builder(context, serviceToken).buildAsync()
    }

}