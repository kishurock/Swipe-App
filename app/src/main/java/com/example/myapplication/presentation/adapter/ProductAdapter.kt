package com.example.myapplication.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.ProductListItem
import com.example.myapplication.databinding.SingleProductItemBinding
import com.squareup.picasso.Picasso


class ProductAdapter(private val context: Context) : RecyclerView.Adapter<MyViewHolder>() {
    private val productList = ArrayList<ProductListItem>()
    fun setList(products: List<ProductListItem>) {
        productList.clear()
        productList.addAll(products)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: SingleProductItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.single_product_item, parent, false)
        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(productList[position], context)
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}

class MyViewHolder(val binding: SingleProductItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(productListItem: ProductListItem, context: Context) {

        binding.productName.text = productListItem.productName
        binding.productType.text = productListItem.productType
        val tax="${productListItem.tax}%"
        binding.productTax.text = tax
        binding.productPrice.text= String.format("%.2f", productListItem.price)
        val imageUrl = productListItem.image
        if (imageUrl.isEmpty()) {
            binding.productImage.setImageResource(R.mipmap.ic_launcher);
        } else{
            Picasso.get().load(imageUrl).into(binding.productImage);
        }
    }

}