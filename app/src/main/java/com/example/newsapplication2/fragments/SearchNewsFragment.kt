package com.example.newsapplication2.fragments

import android.content.ContentValues
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplication2.MainActivity
import com.example.newsapplication2.R
import com.example.newsapplication2.adapter.NewsAdaptor
import com.example.newsapplication2.databinding.FragmentSearchNewsBinding
import com.example.newsapplication2.requesthandler.Resource
import com.example.newsapplication2.utils.Constants.Companion.SEARCH_TIME_DELAY
import com.example.newsapplication2.viewmodels.NewsViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchNewsFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    private var _binding : FragmentSearchNewsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adaptor: NewsAdaptor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchNewsBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = (activity as MainActivity).viewModel
        adaptor = NewsAdaptor()

        var job: Job? = null
        binding.etSearch.addTextChangedListener {text: Editable? ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_TIME_DELAY)
                text?.let {
                    if (text.toString().isNotEmpty()){
                        viewModel.getSearchNews(text.toString())
                    }
                }
            }
        }

        adaptor.setOnClickListener {
            val bundle = Bundle().apply {putSerializable("article", it)}
            findNavController().navigate(
                    R.id.action_searchNewsFragment_to_articleFragment,
                    bundle
            )
        }


        viewModel.SearchNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        adaptor.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    Log.i(ContentValues.TAG, "onCreateView:error call >>>>>" + response.message)
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

        binding.rvSearch.apply {
            adapter = adaptor
            layoutManager = LinearLayoutManager(activity)
        }

        return view
    }

    private fun showProgressBar() : Unit {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun  hideProgressBar(): Unit {
        binding.progressBar.visibility = View.GONE
    }

}
