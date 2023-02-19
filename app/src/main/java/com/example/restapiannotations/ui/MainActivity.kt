package com.example.restapiannotations.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restapiannotations.R
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
    }
}