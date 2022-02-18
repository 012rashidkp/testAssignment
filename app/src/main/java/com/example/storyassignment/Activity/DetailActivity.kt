package com.example.storyassignment.Activity

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import coil.api.load
import com.example.storyassignment.Adapter.StoryAdapter
import com.example.storyassignment.Interface.ItemClickInterface
import com.example.storyassignment.Model.StoriesItem
import com.example.storyassignment.Network.Status
import com.example.storyassignment.Utils.TinyDb
import com.example.storyassignment.databinding.ActivityDetailBinding
import com.example.storyassignment.databinding.ActivityMainBinding

class DetailActivity : BaseActivity() {

    private var binding: ActivityDetailBinding?=null
    lateinit var storyitems:List<StoriesItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        InitTinyDb()
        InitViewModel()
      val id=tinyDb.getInt("id")
      val status=tinyDb.getBoolean("status")
      if (!status){
          val seened=tinyDb.putBoolean("seened",true)
      }
      else{
         val alreadyseened=true
      }
        MakeApiCall(id)

    }

    private fun MakeApiCall(id: Int) {
        viewModel.getsinglestory(id).observe(this,{
            it?.let { resources ->
                when(resources.status){
                    Status.SUCCESS->{
                        resources.data?.let { result->
                            System.out.println("success "+result)
                            Loading()
                            storyitems=result
                        binding!!.storyimg.load(storyitems.get(0).image)
                        binding!!.storytxt.text=storyitems.get(0).title

                        }
                    }
                    Status.LOADING->{

                    }
                    Status.ERROR->{
                        binding!!.storyprogressbar.visibility=View.GONE
                    }
                }
            }
        })


    }
    private fun Loading(){
Handler().postDelayed({
    binding!!.itemlayout.visibility=View.VISIBLE
    binding!!.storyprogressbar.visibility=View.GONE
},2500)
    }
}