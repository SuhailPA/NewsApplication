package com.example.newsapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.newsapp.data.newslist_response.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {

    @Query("SELECT * FROM ARTICLE")
     fun getTopNews() : Flow<List<Article>>

    @Insert
    suspend fun storeTopNews(news : List<Article>)

    @Query("DELETE FROM Article")
    suspend fun deleteNewsFromDB()
}