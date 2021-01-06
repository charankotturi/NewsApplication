package com.example.newsapplication2.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication2.models.NewsResponse
import com.example.newsapplication2.repository.NewsRepository
import com.example.newsapplication2.requesthandler.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
        private val repository: NewsRepository
) : ViewModel() {

    val BreakingNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    private val Pages = 1

    val SearchNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    private val SearchPages = 1

    init {
        getBreakingNews("in")
    }

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        BreakingNews.postValue(Resource.Loading())
        val response = repository.getBreakingNews(countryCode, Pages)
        BreakingNews.postValue(handleBreakingNews(response))
    }

    fun getSearchNews(query: String) = viewModelScope.launch {
        SearchNews.postValue(Resource.Loading())
        val response = repository.getSearchNews(query, SearchPages)
        SearchNews.postValue(handleSearchNews(response))
    }

    private fun handleBreakingNews(response: Response<NewsResponse>) : Resource<NewsResponse>{
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }

        return Resource.Error(response.message())
    }

    private fun handleSearchNews(response: Response<NewsResponse>) : Resource<NewsResponse>{
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }

        return Resource.Error(response.message())
    }

}