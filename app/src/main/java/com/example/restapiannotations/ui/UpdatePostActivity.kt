package com.example.restapiannotations.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.restapiannotations.databinding.ActivityCreatePostBinding
import com.example.restapiannotations.databinding.ActivityUpdatePostBinding
import com.example.restapiannotations.model.Post
import com.example.restapiannotations.repository.PostRepository
import com.example.restapiannotations.util.Resource
import com.example.restapiannotations.viewmodel.PostViewModel
import com.example.restapiannotations.viewmodel.PostViewModelProviderFactory

class UpdatePostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdatePostBinding

    private lateinit var postViewModel: PostViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postRepository = PostRepository()
        val postViewModelProviderFactory = PostViewModelProviderFactory(postRepository)
        postViewModel = ViewModelProvider(this, postViewModelProviderFactory)[PostViewModel::class.java]

        binding.btnUpdate.setOnClickListener {
            binding.apply {
                val userId = edtUserId.text.toString()
                val id = edtId.text.toString()
                val title = edtTitle.text.toString()
                val body = edtBody.text.toString()
                val post = Post(userId = userId.toInt(), title = title, body = body)
                updatePost(id.toInt(), post)
            }

            postViewModel.postCode.observe(this, Observer {
                Toast.makeText(this@UpdatePostActivity, it.data.toString(), Toast.LENGTH_SHORT).show()
            })
        }
    }

    private fun updatePost(id: Int, post: Post) {
        postViewModel.updatePost.observe(this, Observer { response->
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
        postViewModel.updatePost("909", id, post)
    }
}