package com.example.newsapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.newslist_response.Article
import com.example.newsapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repository: NewsRepository, val apiKey: String) :
    ViewModel() {
    val newsFlow = MutableLiveData<List<Article>>()

    init {
        refreshNews("in", apiKey)
        getAllNews()
    }

    fun refreshNews(country: String, apiKey: String) {
        viewModelScope.launch {
            repository.refreshTopHeadlines(country, apiKey)
        }
    }

    private fun getAllNews() {
        viewModelScope.launch {
            repository.news.collect {
                newsFlow.value = it
            }
        }
    }
}