package com.example.newsapplication2.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplication2.MainActivity
import com.example.newsapplication2.R
import com.example.newsapplication2.adapter.NewsAdaptor
import com.example.newsapplication2.databinding.FragmentBreakingNewsBinding
import com.example.newsapplication2.requesthandler.Resource
import com.example.newsapplication2.viewmodels.NewsViewModel


class BreakingNewsFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    private var _binding : FragmentBreakingNewsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adaptor: NewsAdaptor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreakingNewsBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = (activity as MainActivity).viewModel
        adaptor = NewsAdaptor()

        viewModel.BreakingNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        adaptor.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    Log.i(TAG, "onCreateView:error call >>>>>" + response.message)
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

        adaptor.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                    R.id.action_breakingNewsFragment_to_articleFragment,
                    bundle
            )
        }


        binding.rvBreakingNews.apply {
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