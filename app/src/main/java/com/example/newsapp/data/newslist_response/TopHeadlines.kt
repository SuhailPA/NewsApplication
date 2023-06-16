package com.example.newsapp.data.newslist_response

data class TopHeadlines(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)