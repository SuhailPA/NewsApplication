package com.example.newsapp.repository

import com.example.newsapp.data.NewsAPIInterface
import com.example.newsapp.data.newslist_response.TopHeadlines
import retrofit2.Response
import javax.inject.Inject

 class NewsRepository @Inject constructor(private val newsResponse : NewsAPIInterface){

   suspend fun getAllTopNews(country : String, apiKey : String) : Response<TopHeadlines> = newsResponse.getTopHeadlines(country,apiKey)
}