package com.example.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.data.newslist_response.Article
import com.example.newsapp.databinding.FragmentDetailedBinding


class DetailedFragment : Fragment() {

    private val newsArg: DetailedFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailedBinding.inflate(inflater, container, false)
        initUI()
        return binding.root
    }

    private fun initUI() {
        newsArg.newsItem?.apply {
            Glide.with(binding.imageView2.context)
                .load(urlToImage)
                .placeholder(R.drawable.newspaper_icon_new)
                .error(R.drawable.newspaper_icon_new)
                .centerCrop()
                .into(binding.imageView2)
            binding.newsTitle.text = title
            binding.publishedDate.text = publishedAt
            binding.newsContent.text = content
        }
    }


}