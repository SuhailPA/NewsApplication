package com.example.newsapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.data.newslist_response.Article

@Database (entities = [Article::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao() : NewsDao
}