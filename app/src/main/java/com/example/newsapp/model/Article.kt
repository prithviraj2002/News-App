package com.example.newsapp.model

data class Article(
    val author: Any? = "News App",
    val content: Any? = "News app defaults",
    val description: Any? = "News app default configuration",
    val publishedAt: String? = "News app published at",
    val source: Source?,
    val title: String? = "News app title",
    val url: String? = "News app url",
    val urlToImage: Any? = "https://kubrick.htvapps.com/htv-prod/ibmig/cms/image/wesh/27610772-27610772.jpg?crop=1.00xw:1.00xh;0,0&resize=1200:"
)