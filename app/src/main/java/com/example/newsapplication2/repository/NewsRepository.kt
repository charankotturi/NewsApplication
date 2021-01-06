package com.example.newsapplication2.repository

import com.example.newsapplication2.api.RetrofitInstance
import com.example.newsapplication2.database.ArticleDatabase

class NewsRepository(
    val db: ArticleDatabase
){

    suspend fun getBreakingNews(countryCode: String, pages: Int) =
            RetrofitInstance.api.getBreakingNews(countryCode, pages)

    suspend fun getSearchNews(query: String, pages: Int) =
            RetrofitInstance.api.getSearchNews(query, pages)

}