package com.example.storyassignment.Interface

import com.example.storyassignment.Model.StoriesItem
import com.example.storyassignment.Network.Apis.Companion.stories
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET(stories)
    suspend fun GetStories():List<StoriesItem>
    @GET(stories)
    suspend fun GetSinglestory(@Query("id")id:Int):List<StoriesItem>

}