package com.example.newsapplication2.models

import com.example.newsapplication2.models.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)