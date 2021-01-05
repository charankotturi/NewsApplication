package com.example.newsapplication2.api

import com.example.newsapplication2.models.NewsResponse
import com.example.newsapplication2.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("/v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        country: String = "us",
        @Query("page")
        page: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>

    @GET("/v2/everything")
    suspend fun getSearchNews(
        @Query("q")
        country: String,
        @Query("page")
        page: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>

}