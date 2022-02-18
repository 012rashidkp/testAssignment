package com.example.storyassignment.Model

data class StoriesItem(
    val id: Int,
    val image: String,
    val profile: Profile,
    val read_status: Boolean,
    val title: String
)
