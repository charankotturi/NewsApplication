package com.example.newsapplication2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapplication2.MainActivity
import com.example.newsapplication2.R
import com.example.newsapplication2.viewmodels.NewsViewModel

class SavedNewsFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = (activity as MainActivity).viewModel

        return inflater.inflate(R.layout.fragment_saved_news, container, false)
    }

}