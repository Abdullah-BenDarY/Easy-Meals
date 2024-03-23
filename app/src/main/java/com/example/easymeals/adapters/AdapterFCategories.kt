package com.example.easymeals.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easymeals.databinding.ItemFCategoryBinding
import com.example.easymeals.pojo.Category

class AdapterFCategories: RecyclerView.Adapter<AdapterFCategories.Holder>() {
    var categoryList: List<Category>? = null
    private lateinit var onClick: (String) -> Unit?
    fun setOnClick(onClick: (String) -> Unit) {
        this.onClick = onClick}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemFCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList?.size ?: 0
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = categoryList!![position]
        holder.bind(data)
    }

    inner class Holder(val binding: ItemFCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onClick.invoke(categoryList!![layoutPosition].strCategory)
            }
        }

        fun bind(categoryList: Category) {
            binding.apply {
                Glide.with(binding.root.context)
                    .load(categoryList.strCategoryThumb)
                    .into(imgMeal)
                tvCategoryName.text = categoryList.strCategory
                tvCategoryDisc.text = categoryList.strCategoryDescription

            }

        }
    }



}