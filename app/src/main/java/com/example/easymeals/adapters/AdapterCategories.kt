package com.example.easymeals.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easymeals.pojo.Category
import com.example.easymeals.databinding.ItemCategoryBinding

class AdapterCategories : RecyclerView.Adapter<AdapterCategories.Holder>() {
    var categoryList: List<Category>? = null
    private lateinit var onClick: (String) -> Unit?
    fun setOnClick(onClick: (String) -> Unit) {
        this.onClick = onClick}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList?.size ?: 0
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = categoryList!![position]
        holder.bind(data)
    }

    inner class Holder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onClick.invoke(categoryList!![layoutPosition].strCategory)
            }
        }

        fun bind(categoryList: Category) {
            binding.apply {
                Glide.with(binding.root.context)
                    .load(categoryList.strCategoryThumb)
                    .into(imgCategory)
                tvCategoryName.text = categoryList.strCategory

            }

        }
    }



}