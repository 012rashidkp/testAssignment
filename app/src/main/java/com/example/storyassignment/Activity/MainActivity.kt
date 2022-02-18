package com.example.storyassignment.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storyassignment.Adapter.StoryAdapter
import com.example.storyassignment.Interface.ItemClickInterface
import com.example.storyassignment.Model.StoriesItem
import com.example.storyassignment.Network.ApiClient
import com.example.storyassignment.Network.ApiHelper
import com.example.storyassignment.Network.Status
import com.example.storyassignment.R
import com.example.storyassignment.Utils.TinyDb
import com.example.storyassignment.ViewModel.StoryViewModel
import com.example.storyassignment.ViewModelFactory.StoryViewModelFactory
import com.example.storyassignment.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {


    lateinit var adapter: StoryAdapter
    lateinit var storyitems:List<StoriesItem>
    private var binding: ActivityMainBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        InitViewModel()
        InitTinyDb()
        MakeApiCall()

    }

    private fun MakeApiCall() {
        viewModel.getstories().observe(this,{
            it?.let { resources ->
                when(resources.status){
                    Status.SUCCESS->{
                        resources.data?.let { result->
                            System.out.println("success "+result)
                            Loading()
                            storyitems=result
                            adapter= StoryAdapter(this,storyitems,object: ItemClickInterface {
                                override fun ItemClick(id: Int,status:Boolean) {
                                    tinyDb.putInt("id",id)
                                   tinyDb.putBoolean("status",status)
                           val intent=Intent(this@MainActivity,DetailActivity::class.java)
                                    overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                                    startActivity(intent)


                                }

                            })
                            binding!!.storylist.adapter=adapter
                            adapter.notifyDataSetChanged()


                        }
                    }
                    Status.LOADING->{

                    }
                    Status.ERROR->{

                    }
                }
            }
        })
    }


    private fun Loading(){
        Handler().postDelayed({
            binding!!.storylist.visibility= View.VISIBLE
            binding!!.storyprogressbar.visibility= View.GONE
            binding!!.storylist.layoutManager= LinearLayoutManager(this)
        },2500)
    }
}