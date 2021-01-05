package com.example.newsapplication2.adapter

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapplication2.R
import com.example.newsapplication2.databinding.ActivityMainBinding.inflate
import com.example.newsapplication2.models.Article
import java.util.*

class NewsAdaptor(
    context: Context
) : RecyclerView.Adapter<NewsAdaptor.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imgImage: ImageView = itemView.findViewById<ImageView>(R.id.imgImage)
        val txtTitle: TextView = itemView.findViewById<TextView>(R.id.txtTitle)
        val txtDescription: TextView = itemView.findViewById<TextView>(R.id.txtDescription)
        val txtAuthor: TextView = itemView.findViewById<TextView>(R.id.txtAuthor)
    }

    private val diffList = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return  oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffList)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.model,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        Glide.with(holder.itemView)
            .asBitmap()
            .load(article.urlToImage)
            .into(holder.imgImage)
        holder.txtTitle.text = article.title
        holder.txtAuthor.text = article.author
        holder.txtDescription.text = article.description

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}