package com.example.newsapp.network

import com.example.newsapp.model.News
import com.example.newsapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NewsApi {
    @GET(value = "top-headlines")
    suspend fun getNews(
        @Query("country") country: String = "in",
        @Query("category") category: String,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): News
}