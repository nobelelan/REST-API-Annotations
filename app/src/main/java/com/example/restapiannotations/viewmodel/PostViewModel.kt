package com.example.restapiannotations.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restapiannotations.model.Comment
import com.example.restapiannotations.model.Post
import com.example.restapiannotations.repository.PostRepository
import com.example.restapiannotations.util.Resource
import kotlinx.coroutines.launch

class PostViewModel(private val postRepository: PostRepository): ViewModel() {

    private val _getPosts = MutableLiveData<Resource<List<Post>>>()
    val getPosts: LiveData<Resource<List<Post>>>
        get() = _getPosts

    private val _getComments = MutableLiveData<Resource<List<Comment>>>()
    val getComments: LiveData<Resource<List<Comment>>>
        get() = _getComments

    private val _getCommentsQuery = MutableLiveData<Resource<List<Comment>>>()
    val getCommentsQuery: LiveData<Resource<List<Comment>>>
        get() = _getCommentsQuery

    private val _getCommentsQueryMap = MutableLiveData<Resource<List<Post>>>()
    val getCommentsQueryMap: LiveData<Resource<List<Post>>>
        get() = _getCommentsQueryMap

    private val _getCommentsOnUrl = MutableLiveData<Resource<List<Comment>>>()
    val getCommentsOnUrl: LiveData<Resource<List<Comment>>>
        get() = _getCommentsOnUrl

    private val _createPost = MutableLiveData<Resource<Post>>()
    val createPost: LiveData<Resource<Post>>
        get() = _createPost

    private val _createPostField = MutableLiveData<Resource<Post>>()
    val createPostField: LiveData<Resource<Post>>
        get() = _createPostField

    private val _createPostFieldMap = MutableLiveData<Resource<Post>>()
    val createPostFieldMap: LiveData<Resource<Post>>
        get() = _createPostFieldMap



    private val _createPostCode = MutableLiveData<Resource<Int>>()
    val createPostCode: LiveData<Resource<Int>>
        get() = _createPostCode

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

    fun getComments(postId: Int){
        _getComments.value = Resource.Loading()
        viewModelScope.launch {
            try {
                val response = postRepository.getComments(postId)
                if (response.isSuccessful){
                    response.body()?.let {
                        _getComments.value = Resource.Success(it)
                    }
                }
            }catch (e: Exception){
                _getComments.value = Resource.Error(e.message.toString())
            }
        }
    }

    fun getCommentsQuery(postId: Int){
        _getCommentsQuery.value = Resource.Loading()
        viewModelScope.launch {
            try {
                val response = postRepository.getCommentsQuery(postId)
                if (response.isSuccessful){
                    response.body()?.let {
                        _getCommentsQuery.value = Resource.Success(it)
                    }
                }
            }catch (e: Exception){
                _getCommentsQuery.value = Resource.Error(e.message.toString())
            }
        }
    }

    fun getCommentsQueryMap(parameters: Map<String, String>){
        _getCommentsQueryMap.value = Resource.Loading()
        viewModelScope.launch {
            try {
                val response = postRepository.getCommentsQueryMap(parameters)
                if (response.isSuccessful){
                    response.body()?.let {
                        _getCommentsQueryMap.value = Resource.Success(it)
                    }
                }
            }catch (e: Exception){
                _getCommentsQueryMap.value = Resource.Error(e.message.toString())
            }
        }
    }

    fun getCommentsOnUrl(url: String){
        _getCommentsOnUrl.value = Resource.Loading()
        viewModelScope.launch {
            try {
                val response = postRepository.getCommentsOnUrl(url)
                if (response.isSuccessful){
                    response.body()?.let {
                        _getCommentsOnUrl.value = Resource.Success(it)
                    }
                }
            }catch (e: Exception){
                _getCommentsOnUrl.value = Resource.Error(e.message.toString())
            }
        }
    }

    fun createPost(post: Post){
        _createPost.value = Resource.Loading()
        viewModelScope.launch {
            try {
                val response = postRepository.createPost(post)
                if (response.isSuccessful){
                    response.body()?.let {
                        _createPost.value = Resource.Success(it)
                        _createPostCode.value = Resource.Success(response.code())
                    }
                }
            }catch (e: Exception){
                _createPost.value = Resource.Error(e.message.toString())
            }
        }
    }

    fun createPostField(userId: Int, title: String, body: String){
        _createPostField.value = Resource.Loading()
        viewModelScope.launch {
            try {
                val response = postRepository.createPostField(userId, title, body)
                if (response.isSuccessful){
                    response.body()?.let {
                        _createPostField.value = Resource.Success(it)
                        _createPostCode.value = Resource.Success(response.code())
                    }
                }
            }catch (e: Exception){
                _createPostField.value = Resource.Error(e.message.toString())
            }
        }
    }

    fun createPostFieldMap(fields: Map<String, String>){
        _createPostFieldMap.value = Resource.Loading()
        viewModelScope.launch {
            try {
                val response = postRepository.createPostFieldMap(fields)
                if (response.isSuccessful){
                    response.body()?.let {
                        _createPostFieldMap.value = Resource.Success(it)
                        _createPostCode.value = Resource.Success(response.code())
                    }
                }
            }catch (e: Exception){
                _createPostFieldMap.value = Resource.Error(e.message.toString())
            }
        }
    }
}