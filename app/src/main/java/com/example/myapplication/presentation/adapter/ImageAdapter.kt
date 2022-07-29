package com.example.myapplication.presentation.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.ProductListItem
import com.example.myapplication.databinding.SingleImageItemBinding
import com.example.myapplication.databinding.SingleProductItemBinding
import com.squareup.picasso.Picasso

class ImageAdapter(private val context: Context, private val imageList: ArrayList<Uri>) :
    RecyclerView.Adapter<MyViewHolder2>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder2 {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: SingleImageItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.single_image_item, parent, false)
        return MyViewHolder2(binding)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder2,
        position: Int
    ) {
        holder.bind(imageList[position], context)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}

class MyViewHolder2(private val binding: SingleImageItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(imageListItem: Uri, context: Context) {

        Picasso.get().load(imageListItem).error(R.mipmap.ic_launcher).into(binding.iivImage)
//        Glide.with(binding.iivImage.context)
//            .load(imageListItem.path)
//            .error(R.drawable.ic_launcher_background)
//            .into(binding.iivImage)
    }

}