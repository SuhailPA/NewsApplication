package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.data.newslist_response.Article
import com.example.newsapp.databinding.NewsItemLayoutBinding

class NewsAdapter :
    RecyclerView.Adapter<NewsAdapter.ItemViewHolder>() {


    inner class ItemViewHolder(private val binding: NewsItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.newsHeading.text = article.title
            binding.publishedTime.text = article.publishedAt
            Glide.with(binding.imageView.context)
                .load(article.urlToImage)
                .placeholder(R.drawable.newspaper_icon_new)
                .error(R.drawable.newspaper_icon_new)
                .centerCrop()
                .into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            NewsItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = differ.currentList.size


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffCallBack)

}