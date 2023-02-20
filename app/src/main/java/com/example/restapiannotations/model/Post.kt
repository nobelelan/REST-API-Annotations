package com.example.restapiannotations.model

data class Post(
    val body: String? = null,
    val id: Int = 0,
    val title: String? = null,
    val userId: Int
)