package com.creativeitinstitute.mvvmcleanecomrestapi.views.product

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.creativeitinstitute.mvvmcleanecomrestapi.data.model.product.ResponseProductItem
import com.creativeitinstitute.mvvmcleanecomrestapi.databinding.ItemProductBinding
import dagger.hilt.android.AndroidEntryPoint


class ProductAdapter(var listener: Listener) :
    ListAdapter<ResponseProductItem, ProductAdapter.ProductViewHolder>(COMPARATOR) {

    interface Listener {
        fun productClick(productId: Int)
    }

    class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {

        val COMPARATOR = object : DiffUtil.ItemCallback<ResponseProductItem>() {
            override fun areItemsTheSame(
                oldItem: ResponseProductItem, newItem: ResponseProductItem
            ): Boolean {

                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ResponseProductItem, newItem: ResponseProductItem
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false),

            )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        getItem(position).let {
            holder.binding.titleTextView.text = it.title
            holder.binding.descriptionTextView.text = it.description
            holder.binding.priceTextView.text = "Price : $${it.price}"
            holder.binding.image1.load(it.images?.get(0))

            Log.d("TAG", "IMAGES: ${it.images?.get(0)} ")



//            it.images?.let { imgUrls ->
//                for (i in imgUrls.indices) {
//                    if (i == 0) {
//                        holder.binding.image1.load( it.images[i])
//                    }
//                    if (i == 1) {
//                        holder.binding.image2.load( it.images[i])
//                    }
//                    if (i == 2) {
//                        holder.binding.image3.load( it.images[i])
//                    }
//
//
//                }
//            }






            it.category?.let { ctg ->
                holder.binding.categoryNameTextView.text = ctg.name
                holder.binding.categoryImageView.load(ctg.image!!)

            }

            holder.itemView.setOnClickListener { _ ->

                it.id?.let { it1 -> listener.productClick(it1) }


            }


        }
    }

}