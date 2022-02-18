package com.example.storyassignment.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.storyassignment.Network.ApiHelper
import com.example.storyassignment.Repository.StoryRepository
import com.example.storyassignment.ViewModel.StoryViewModel

class StoryViewModelFactory(private val apihelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StoryViewModel::class.java)) {
            return StoryViewModel(StoryRepository(apihelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}