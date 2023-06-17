package com.example.newsapp.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.data.NewsAPIInterface
import com.example.newsapp.data.database.NewsDao
import com.example.newsapp.data.database.NewsDatabase
import com.example.newsapp.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    @Singleton
    fun providesNewsInterface(retrofit: Retrofit): NewsAPIInterface =
        retrofit.create(NewsAPIInterface::class.java)

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsAPIInterface: NewsAPIInterface, newsDao: NewsDao
    ): NewsRepository = NewsRepository(newsAPIInterface, newsDao)

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): NewsDatabase =
        Room.databaseBuilder(application, NewsDatabase::class.java, "news_database").build()

    @Provides
    @Singleton
    fun provideDao(database: NewsDatabase): NewsDao = database.newsDao()
}