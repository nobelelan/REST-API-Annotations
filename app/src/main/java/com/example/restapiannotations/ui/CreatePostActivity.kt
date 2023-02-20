package com.example.restapiannotations.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.restapiannotations.databinding.ActivityCreatePostBinding
import com.example.restapiannotations.model.Post
import com.example.restapiannotations.repository.PostRepository
import com.example.restapiannotations.util.Resource
import com.example.restapiannotations.viewmodel.PostViewModel
import com.example.restapiannotations.viewmodel.PostViewModelProviderFactory

class CreatePostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePostBinding

    private lateinit var postViewModel: PostViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postRepository = PostRepository()
        val postViewModelProviderFactory = PostViewModelProviderFactory(postRepository)
        postViewModel = ViewModelProvider(this, postViewModelProviderFactory)[PostViewModel::class.java]

        binding.btnPost.setOnClickListener {
            binding.apply {
                val userId = edtUserId.text.toString()
                val title = edtTitle.text.toString()
                val body = edtBody.text.toString()
                createPost(Post(userId = userId.toInt(), title = title, body = body))
            }

            postViewModel.postCode.observe(this, Observer {
                Toast.makeText(this@CreatePostActivity, it.data.toString(), Toast.LENGTH_SHORT).show()
            })
        }
    }

    private fun createPost(post: Post) {
        postViewModel.createPost.observe(this, Observer { response->
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
        postViewModel.createPost(post)
    }
}