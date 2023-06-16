package com.example.newsapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.newslist_response.TopHeadlines
import com.example.newsapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repository: NewsRepository): ViewModel() {

    var news = MutableLiveData<TopHeadlines> ()
    init {
        getAllNews("in","857417c014944bc7b92fcbd3cf22c8a1")
    }

    private fun getAllNews(country : String, apiKey : String) {
        viewModelScope.launch {
            news.value = repository.getAllTopNews(country,apiKey).body()
        }

    }
}