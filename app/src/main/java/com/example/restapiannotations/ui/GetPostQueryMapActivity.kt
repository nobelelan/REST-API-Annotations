package com.example.restapiannotations.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restapiannotations.adapter.GetPostAdapter
import com.example.restapiannotations.databinding.ActivityGetPostQueryMapBinding
import com.example.restapiannotations.repository.PostRepository
import com.example.restapiannotations.util.Resource
import com.example.restapiannotations.viewmodel.PostViewModel
import com.example.restapiannotations.viewmodel.PostViewModelProviderFactory

class GetPostQueryMapActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetPostQueryMapBinding

    private lateinit var getPostAdapter: GetPostAdapter

    private lateinit var postViewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetPostQueryMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()

        val postRepository = PostRepository()
        val postViewModelProviderFactory = PostViewModelProviderFactory(postRepository)
        postViewModel = ViewModelProvider(this, postViewModelProviderFactory)[PostViewModel::class.java]

        binding.btnSubmit.setOnClickListener {
            val hashMap = HashMap<String, String>()
            hashMap["userId"] = binding.edtId.text.toString()
            hashMap["_sort"] = binding.edtOrderBy.text.toString()
            hashMap["_order"] = binding.edtOrderType.text.toString()

            getCommentsQueryMap(hashMap)
        }
    }



    private fun getCommentsQueryMap(parameters: Map<String, String>) {
        postViewModel.getCommentsQueryMap.observe(this, Observer { response->
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
                    getPostAdapter.differ.submitList(response.data)
                }
            }
        })

        postViewModel.getCommentsQueryMap(parameters)
    }

    private fun setUpRecyclerView() = binding.rvGetComment.apply {
        getPostAdapter = GetPostAdapter()
        adapter = getPostAdapter
        layoutManager = LinearLayoutManager(this@GetPostQueryMapActivity)
    }
}