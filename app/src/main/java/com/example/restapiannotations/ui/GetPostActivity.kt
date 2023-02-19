package com.example.restapiannotations.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restapiannotations.R
import com.example.restapiannotations.adapter.GetPostAdapter
import com.example.restapiannotations.databinding.ActivityGetPostBinding
import com.example.restapiannotations.repository.PostRepository
import com.example.restapiannotations.util.Resource
import com.example.restapiannotations.viewmodel.PostViewModel
import com.example.restapiannotations.viewmodel.PostViewModelProviderFactory

class GetPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetPostBinding

    private lateinit var getPostAdapter: GetPostAdapter

    private lateinit var postViewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()

        val postRepository = PostRepository()
        val postViewModelProviderFactory = PostViewModelProviderFactory(postRepository)
        postViewModel = ViewModelProvider(this, postViewModelProviderFactory)[PostViewModel::class.java]

        getPosts()
    }

    private fun getPosts() {
        postViewModel.getPosts.observe(this, Observer { response->
            when(response){
                is Resource.Loading -> {
                    binding.pbGetPost.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.pbGetPost.visibility = View.GONE
                    Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    binding.pbGetPost.visibility = View.GONE
                    getPostAdapter.differ.submitList(response.data)
                }
            }
        })
        postViewModel.getPosts()
    }

    private fun setUpRecyclerView() = binding.rvGetPost.apply {
        getPostAdapter = GetPostAdapter()
        adapter = getPostAdapter
        layoutManager = LinearLayoutManager(this@GetPostActivity)
    }
}