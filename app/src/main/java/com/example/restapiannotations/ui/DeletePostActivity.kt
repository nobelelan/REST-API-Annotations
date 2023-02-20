package com.example.restapiannotations.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.restapiannotations.databinding.ActivityCreatePostBinding
import com.example.restapiannotations.databinding.ActivityDeletePostBinding
import com.example.restapiannotations.databinding.ActivityUpdatePostBinding
import com.example.restapiannotations.model.Post
import com.example.restapiannotations.repository.PostRepository
import com.example.restapiannotations.util.Resource
import com.example.restapiannotations.viewmodel.PostViewModel
import com.example.restapiannotations.viewmodel.PostViewModelProviderFactory

class DeletePostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeletePostBinding

    private lateinit var postViewModel: PostViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeletePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postRepository = PostRepository()
        val postViewModelProviderFactory = PostViewModelProviderFactory(postRepository)
        postViewModel = ViewModelProvider(this, postViewModelProviderFactory)[PostViewModel::class.java]

        binding.btnDelete.setOnClickListener {
            val id = binding.edtId.text.toString()
            deletePost(id.toInt())

            postViewModel.postCode.observe(this, Observer {
                binding.txtCode.text = it.data.toString()
            })
        }
    }

    private fun deletePost(id: Int) {
        postViewModel.deletePost.observe(this, Observer { response->
            when(response){
                is Resource.Loading -> {
                    binding.pbDeletePost.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.pbDeletePost.visibility = View.GONE
                    Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    binding.pbDeletePost.visibility = View.GONE
                }
            }
        })
        postViewModel.deletePost(id)
    }
}