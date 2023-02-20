package com.example.restapiannotations.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restapiannotations.R
import com.example.restapiannotations.adapter.GetCommentAdapter
import com.example.restapiannotations.databinding.ActivityGetCommentUrlBinding
import com.example.restapiannotations.repository.PostRepository
import com.example.restapiannotations.util.Resource
import com.example.restapiannotations.viewmodel.PostViewModel
import com.example.restapiannotations.viewmodel.PostViewModelProviderFactory

class GetCommentUrlActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetCommentUrlBinding

    private lateinit var getCommentAdapter: GetCommentAdapter

    private lateinit var postViewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetCommentUrlBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()

        val postRepository = PostRepository()
        val postViewModelProviderFactory = PostViewModelProviderFactory(postRepository)
        postViewModel = ViewModelProvider(this, postViewModelProviderFactory)[PostViewModel::class.java]

        getCommentsOnUrl("/posts/1/comments")
    }

    private fun getCommentsOnUrl(url: String) {
        postViewModel.getCommentsOnUrl.observe(this, Observer { response->
            when(response){
                is Resource.Loading -> {
                    binding.pbGetCommentUrl.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.pbGetCommentUrl.visibility = View.GONE
                    Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    binding.pbGetCommentUrl.visibility = View.GONE
                    getCommentAdapter.differ.submitList(response.data)
                }
            }
        })
        postViewModel.getCommentsOnUrl(url)
    }

    private fun setUpRecyclerView() = binding.rvGetCommentUrl.apply {
        getCommentAdapter = GetCommentAdapter()
        adapter = getCommentAdapter
        layoutManager = LinearLayoutManager(this@GetCommentUrlActivity)
    }
}