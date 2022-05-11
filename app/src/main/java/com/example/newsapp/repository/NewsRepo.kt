package com.example.newsapp.repository

import com.example.newsapp.data.DataOrException
import com.example.newsapp.model.News
import com.example.newsapp.network.NewsApi
import javax.inject.Inject

class NewsRepo @Inject constructor(private val newsApi: NewsApi) {
    suspend fun getNews(country: String, category: String): DataOrException<News, Boolean, Exception>{
        val response = try {
            newsApi.getNews(country, category)
        }catch (exception: Exception){
            return DataOrException(E = exception)
        }
        return DataOrException(data = response)
    }
}