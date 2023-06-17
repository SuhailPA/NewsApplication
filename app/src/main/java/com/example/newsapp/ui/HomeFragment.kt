package com.example.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        initUI()
        getNews()
        return binding?.root
    }

    /**
     * Initializing the UI
     */
    private fun initUI() {
        newsAdapter = NewsAdapter()
        binding?.newsRecyclerView?.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context)
        }
        newsAdapter.setOnItemClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailedFragment(it)
            findNavController().navigate(action)
        }
        binding?.swiperefresh?.setOnRefreshListener {
            viewModel.refreshNews()
        }
    }

    /**
     * Method to get the latest news from API
     */
    private fun getNews() {
        viewModel.newsFlow.observe(viewLifecycleOwner) {
            newsAdapter.differ.submitList(it)
            binding?.swiperefresh?.isRefreshing = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}