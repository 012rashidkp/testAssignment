package com.example.storyassignment.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.storyassignment.Interface.ItemClickInterface
import com.example.storyassignment.Model.StoriesItem
import com.example.storyassignment.R
import com.example.storyassignment.Utils.TinyDb
import com.example.storyassignment.databinding.StorylistBinding

class StoryAdapter(private val context: Context, private val items: List<StoriesItem>, private val listener: ItemClickInterface):
    RecyclerView.Adapter<StoryAdapter.MyViewHolder>() {
lateinit var tinyDb: TinyDb
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=StorylistBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        tinyDb= TinyDb(context)
        val status=tinyDb.getBoolean("seened")
        val datas=items[position]
        with(holder){
            binding.storyname.text=datas.title

            binding.profileImage.load(datas.profile.image)
            if (datas.read_status){
                binding.seenimg.load(R.drawable.seened)
            }

            itemView.setOnClickListener { listener.ItemClick(datas.id,datas.read_status)

            }

//            binding.updatebtn.setOnClickListener { listener.ItemClick(datas.file_id,position,"update") }
//            binding.deletebtn.setOnClickListener { listener.ItemClick(datas.file_id,position,"delete") }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }




    class MyViewHolder(val binding: StorylistBinding): RecyclerView.ViewHolder(binding.root) {

    }
}