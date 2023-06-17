package com.example.newsapp.repository

import com.example.newsapp.data.NewsAPIInterface
import com.example.newsapp.data.database.NewsDao
import com.example.newsapp.data.newslist_response.TopHeadlines
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsResponse: NewsAPIInterface,
    private val dao: NewsDao
) {

    val news = dao.getTopNews()
    suspend fun refreshTopHeadlines(country: String, apiKey: String) {
        try {
            val response = newsResponse.getTopHeadlines(country, apiKey)
            if (response.body()?.status == "ok") {
                response.body()?.articles?.let {
                    dao.deleteNewsFromDB()
                    dao.storeTopNews(it)
                }
            }
        } catch (e: Exception) {
            e.stackTrace
        }


    }

}