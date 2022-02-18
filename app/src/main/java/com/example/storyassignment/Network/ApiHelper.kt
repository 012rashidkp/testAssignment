package com.example.storyassignment.Network

import com.example.storyassignment.Interface.ApiInterface

class ApiHelper  (private val apiInterface: ApiInterface) {
    suspend fun getstories()=apiInterface.GetStories()
    suspend fun getsinglestory(id:Int)=apiInterface.GetSinglestory(id)

}