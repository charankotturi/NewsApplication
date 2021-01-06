package com.example.newsapplication2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.newsapplication2.database.ArticleDatabase
import com.example.newsapplication2.databinding.ActivityMainBinding
import com.example.newsapplication2.repository.NewsRepository
import com.example.newsapplication2.viewmodels.NewsViewModel
import com.example.newsapplication2.viewmodels.NewsViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = NewsRepository(ArticleDatabase(this))
        val providerFactory = NewsViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, providerFactory).get(NewsViewModel::class.java)

        val navController = Navigation.findNavController(this , R.id.fragment)
        binding.bottomNavView.setupWithNavController(navController)

    }
}