package com.example.storyassignment.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.storyassignment.Network.ApiClient
import com.example.storyassignment.Network.ApiHelper
import com.example.storyassignment.Utils.TinyDb
import com.example.storyassignment.ViewModel.StoryViewModel
import com.example.storyassignment.ViewModelFactory.StoryViewModelFactory

open class BaseActivity : AppCompatActivity() {
    lateinit var viewModel: StoryViewModel
    lateinit var tinyDb: TinyDb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    fun InitViewModel(){
        viewModel= ViewModelProvider(
            this,
            StoryViewModelFactory(ApiHelper(ApiClient.invoke()))
        )[StoryViewModel::class.java]
    }
    fun InitTinyDb(){
        tinyDb=TinyDb(this)
    }
}