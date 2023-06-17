package com.example.newsapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.newslist_response.Article
import com.example.newsapp.data.newslist_response.TopHeadlines
import com.example.newsapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repository: NewsRepository): ViewModel() {
//
//    private val _newsFlow = MutableSharedFlow<List<Article>>()
    val newsFlow = MutableLiveData<List<Article>>()


    init {
        refreshNews()
        getAllNews()
    }

     fun refreshNews() {
        viewModelScope.launch {
            repository.refreshTopHeadlines("in","857417c014944bc7b92fcbd3cf22c8a1")
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