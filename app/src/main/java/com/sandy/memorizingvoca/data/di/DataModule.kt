package com.sandy.memorizingvoca.data.di

import com.sandy.memorizingvoca.data.network.VocaDetailsDataSource
import com.sandy.memorizingvoca.data.network.VocaDetailsDataSourceImpl
import com.sandy.memorizingvoca.data.repository.BookmarkRepository
import com.sandy.memorizingvoca.data.repository.GetQuizRepository
import com.sandy.memorizingvoca.data.repository.GetVocabularyRepository
import com.sandy.memorizingvoca.data.repository.HighlightRepository
import com.sandy.memorizingvoca.data.repository.QuizRepository
import com.sandy.memorizingvoca.data.repository.VocabularyRepository
import com.sandy.memorizingvoca.data.repository.impl.BookmarkRepositoryImpl
import com.sandy.memorizingvoca.data.repository.impl.GetQuizRepositoryImpl
import com.sandy.memorizingvoca.data.repository.impl.GetVocabularyRepositoryImpl
import com.sandy.memorizingvoca.data.repository.impl.HighlightRepositoryImpl
import com.sandy.memorizingvoca.data.repository.impl.QuizRepositoryImpl
import com.sandy.memorizingvoca.data.repository.impl.VocabularyRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataModule {

    @Binds
    abstract fun bindsBookmarkRepository(
        impl: BookmarkRepositoryImpl
    ): BookmarkRepository

    @Binds
    abstract fun bindsGetQuizRepository(
        impl: GetQuizRepositoryImpl
    ): GetQuizRepository

    @Binds
    abstract fun bindsGetVocabularyRepository(
        impl: GetVocabularyRepositoryImpl
    ): GetVocabularyRepository

    @Binds
    abstract fun bindsHighlightRepository(
        impl: HighlightRepositoryImpl
    ): HighlightRepository

    @Binds
    abstract fun bindsQuizRepository(
        impl: QuizRepositoryImpl
    ): QuizRepository

    @Binds
    abstract fun bindsVocabularyRepository(
        impl: VocabularyRepositoryImpl
    ): VocabularyRepository

    @Binds
    abstract fun bindsVocaDetailsDataSource(
        impl: VocaDetailsDataSourceImpl
    ): VocaDetailsDataSource

}