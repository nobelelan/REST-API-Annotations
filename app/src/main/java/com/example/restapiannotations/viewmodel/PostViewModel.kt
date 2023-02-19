package com.example.restapiannotations.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restapiannotations.model.Post
import com.example.restapiannotations.repository.PostRepository
import com.example.restapiannotations.util.Resource
import kotlinx.coroutines.launch

class PostViewModel(private val postRepository: PostRepository): ViewModel() {

    private val _getPosts = MutableLiveData<Resource<List<Post>>>()
    val getPosts: LiveData<Resource<List<Post>>>
        get() = _getPosts

    fun getPosts(){
        _getPosts.value = Resource.Loading()
        viewModelScope.launch {
            try {
                val response = postRepository.getPosts()
                if (response.isSuccessful){
                    response.body()?.let {
                        _getPosts.value = Resource.Success(it)
                    }
                }
            }catch (e: Exception){
                _getPosts.value = Resource.Error(e.message.toString())
            }
        }
    }
}