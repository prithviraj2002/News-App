package com.example.newsapp.screens.screens.homeScreen.homeScreen

import androidx.lifecycle.ViewModel
import com.example.newsapp.data.DataOrException
import com.example.newsapp.model.News
import com.example.newsapp.repository.NewsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val repo: NewsRepo): ViewModel(){
    suspend fun getNews(country: String, category: String): DataOrException<News, Boolean, Exception>{
        return repo.getNews(country, category)
    }
}