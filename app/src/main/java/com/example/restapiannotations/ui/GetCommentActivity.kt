package com.example.restapiannotations.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restapiannotations.adapter.GetCommentAdapter
import com.example.restapiannotations.databinding.ActivityGetCommentBinding
import com.example.restapiannotations.repository.PostRepository
import com.example.restapiannotations.util.Resource
import com.example.restapiannotations.viewmodel.PostViewModel
import com.example.restapiannotations.viewmodel.PostViewModelProviderFactory

class GetCommentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetCommentBinding

    private lateinit var getCommentAdapter: GetCommentAdapter

    private lateinit var postViewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()

        val postRepository = PostRepository()
        val postViewModelProviderFactory = PostViewModelProviderFactory(postRepository)
        postViewModel = ViewModelProvider(this, postViewModelProviderFactory)[PostViewModel::class.java]


        getComments(1)
        searchByPostId()
    }

    private fun searchByPostId(){
        binding.svGetComment.isSubmitButtonEnabled = true
        binding.svGetComment.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    getComments(query.toInt())
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun getComments(postId: Int) {
        postViewModel.getComments.observe(this, Observer { response->
            when(response){
                is Resource.Loading -> {
                    binding.pbGetComment.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.pbGetComment.visibility = View.GONE
                    Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    binding.pbGetComment.visibility = View.GONE
                    getCommentAdapter.differ.submitList(response.data)
                }
            }
        })
        postViewModel.getComments(postId)
    }

    private fun setUpRecyclerView() = binding.rvGetComment.apply {
        getCommentAdapter = GetCommentAdapter()
        adapter = getCommentAdapter
        layoutManager = LinearLayoutManager(this@GetCommentActivity)
    }
}