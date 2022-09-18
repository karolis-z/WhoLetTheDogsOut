package com.myapplications.wholetthedogsout.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myapplications.wholetthedogsout.R
import com.myapplications.wholetthedogsout.databinding.ListItemCardPhotoBinding

class PhotosAdapter(private var urlsAndSumsList : List<Pair<String,Int>>,
                    private val context : Context,
                    private val onItemClicked: (url:String)->Unit)
    : RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    class ViewHolder(binding : ListItemCardPhotoBinding) : RecyclerView.ViewHolder(binding.root){
        val imgPhoto = binding.imgPhoto
        val tvSum = binding.tvSum
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemCardPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("PhotosAdapter", "url to load: ${urlsAndSumsList[position].first}")

        Glide.with(context)
            .load(urlsAndSumsList[position].first)
//            .centerCrop()
            .placeholder(R.drawable.placeholder_dog)
            .into(holder.imgPhoto)

        holder.tvSum.text = urlsAndSumsList[position].second.toString()

        holder.itemView.setOnClickListener {
            Log.d("PhotosAdapter", "url: ${urlsAndSumsList[position].first}")
            onItemClicked.invoke(urlsAndSumsList[position].first)
        }
    }

    override fun getItemCount(): Int {
        return urlsAndSumsList.size
    }
}