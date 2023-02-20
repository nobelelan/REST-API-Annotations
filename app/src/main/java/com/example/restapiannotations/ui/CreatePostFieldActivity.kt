package com.example.restapiannotations.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.restapiannotations.databinding.ActivityCreatePostFieldBinding
import com.example.restapiannotations.model.Post
import com.example.restapiannotations.repository.PostRepository
import com.example.restapiannotations.util.Resource
import com.example.restapiannotations.viewmodel.PostViewModel
import com.example.restapiannotations.viewmodel.PostViewModelProviderFactory

class CreatePostFieldActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePostFieldBinding

    private lateinit var postViewModel: PostViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePostFieldBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postRepository = PostRepository()
        val postViewModelProviderFactory = PostViewModelProviderFactory(postRepository)
        postViewModel = ViewModelProvider(this, postViewModelProviderFactory)[PostViewModel::class.java]

        binding.btnPost.setOnClickListener {
            createPost()
            postViewModel.postCode.observe(this, Observer {
                Toast.makeText(this@CreatePostFieldActivity, it.data.toString(), Toast.LENGTH_SHORT).show()
            })
        }
    }

    private fun createPost() {
        postViewModel.createPostField.observe(this, Observer { response->
            when(response){
                is Resource.Loading -> {
                    binding.pbCreatePost.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.pbCreatePost.visibility = View.GONE
                    Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    binding.pbCreatePost.visibility = View.GONE
                    binding.apply {
                        txtUserId.text = response.data?.userId.toString()
                        txtTitle.text = response.data?.title
                        txtBody.text = response.data?.body
                    }
                }
            }
        })
        val userId = binding.edtUserId.text.toString()
        val title = binding.edtTitle.text.toString()
        val body = binding.edtBody.text.toString()
        postViewModel.createPostField(userId.toInt(), title, body)
    }
}