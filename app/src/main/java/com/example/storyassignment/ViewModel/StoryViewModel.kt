package com.example.storyassignment.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.storyassignment.Network.Resources
import com.example.storyassignment.Repository.StoryRepository
import kotlinx.coroutines.Dispatchers

class StoryViewModel  (private val repository: StoryRepository): ViewModel() {

    fun getstories() = liveData(Dispatchers.IO) {
        emit(Resources.loading(data = null))
        try {
            emit(Resources.success(data = repository.getstories()))
            System.out.println("got result")
        } catch (exception: Exception) {
            emit(Resources.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getsinglestory(id:Int) = liveData(Dispatchers.IO) {
        emit(Resources.loading(data = null))
        try {
            emit(Resources.success(data = repository.getsinglestory(id)))
            System.out.println("got result")
        } catch (exception: Exception) {
            emit(Resources.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}