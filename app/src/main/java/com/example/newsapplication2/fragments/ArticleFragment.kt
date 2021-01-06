package com.example.newsapplication2.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.navArgs
import com.example.newsapplication2.MainActivity
import com.example.newsapplication2.R
import com.example.newsapplication2.databinding.FragmentArticleBinding
import com.example.newsapplication2.viewmodels.NewsViewModel

class ArticleFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    private val args: ArticleFragmentArgs by navArgs()
    private var _binding : FragmentArticleBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater,
                container,
                false)
        val view = binding.root
        val article = args.article

        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }

        viewModel = (activity as MainActivity).viewModel

        return view
    }

}