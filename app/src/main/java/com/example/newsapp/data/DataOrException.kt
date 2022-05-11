package com.example.newsapp.data

data class DataOrException<T, Boolean, Exception>(
    var data: T? = null,
    var loading: Boolean? = null,
    var E: kotlin.Exception? = null
)