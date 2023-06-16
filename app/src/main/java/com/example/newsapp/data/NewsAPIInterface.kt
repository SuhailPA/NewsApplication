package com.example.newsapp.data


import com.example.newsapp.data.newslist_response.TopHeadlines
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIInterface {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country")country : String,
        @Query("apiKey") apikey : String
    ) : Response<TopHeadlines>
}