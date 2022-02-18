package com.example.storyassignment.Repository

import com.example.storyassignment.Network.ApiHelper

class StoryRepository  (private val apiHelper: ApiHelper) {
    suspend fun getstories()=apiHelper.getstories()
    suspend fun getsinglestory(id:Int)=apiHelper.getsinglestory(id)
}