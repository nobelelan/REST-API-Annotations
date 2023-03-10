package com.example.restapiannotations.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restapiannotations.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetPost.setOnClickListener {
            startActivity(Intent(this, GetPostActivity::class.java))
        }
        binding.btnGetComments.setOnClickListener {
            startActivity(Intent(this, GetCommentActivity::class.java))
        }
        binding.btnGetCommentsQuery.setOnClickListener {
            startActivity(Intent(this, GetCommentQueryActivity::class.java))
        }
        binding.btnGetCommentsQueryMap.setOnClickListener {
            startActivity(Intent(this, GetPostQueryMapActivity::class.java))
        }
        binding.btnGetCommentsUrl.setOnClickListener {
            startActivity(Intent(this, GetCommentUrlActivity::class.java))
        }
        binding.btnCreatePost.setOnClickListener {
            startActivity(Intent(this, CreatePostActivity::class.java))
        }
        binding.btnCreatePostField.setOnClickListener {
            startActivity(Intent(this, CreatePostFieldActivity::class.java))
        }
        binding.btnCreatePostFieldMap.setOnClickListener {
            startActivity(Intent(this, CreatePostFieldMapActivity::class.java))
        }
        binding.btnUpdatePost.setOnClickListener {
            startActivity(Intent(this, UpdatePostActivity::class.java))
        }
        binding.btnPatchPost.setOnClickListener {
            startActivity(Intent(this, PatchPostActivity::class.java))
        }
        binding.btnDeletePost.setOnClickListener {
            startActivity(Intent(this, DeletePostActivity::class.java))
        }
    }
}