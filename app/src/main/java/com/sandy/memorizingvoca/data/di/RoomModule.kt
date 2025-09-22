package com.sandy.memorizingvoca.data.di

import android.content.Context
import androidx.room.Room
import com.sandy.memorizingvoca.data.room.MyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val MY_DATABASE_NAME = "voca_database"
    @Singleton
    @Provides
    fun provideMyDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(app, MyDatabase::class.java, MY_DATABASE_NAME)
            .fallbackToDestructiveMigration(false)
            .build()

    @Singleton
    @Provides
    fun provideVocabularyDao(db: MyDatabase) = db.vocabularyDao()

    @Singleton
    @Provides
    fun provideQuizDao(db: MyDatabase) = db.quizDao()

}