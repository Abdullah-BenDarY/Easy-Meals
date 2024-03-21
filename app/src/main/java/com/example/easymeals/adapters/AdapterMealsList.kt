package com.example.easymeals.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easymeals.databinding.ItemCategoryMealsBinding
import com.example.easymeals.pojo.PMeal

class AdapterMealsList : RecyclerView.Adapter<AdapterMealsList.Holder>() {
    var mealList: List<PMeal>? = null
    private lateinit var onClick: (Int) -> Unit?
    fun setOnClick(onClick: (Int) -> Unit) {
        this.onClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMealsList.Holder {
        val binding = ItemCategoryMealsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return mealList?.size ?: 0
    }

    override fun onBindViewHolder(holder: AdapterMealsList.Holder, position: Int) {
        val data = mealList!![position]
        holder.bind(data)
    }

    inner class Holder(private val binding: ItemCategoryMealsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onClick.invoke(mealList!![layoutPosition].idMeal.toInt())
            }
        }

        fun bind(mealList: PMeal) {
            binding.apply {
                Glide.with(binding.root.context)
                    .load(mealList.strMealThumb)
                    .into(imgMeal)
                tvMealName.text = mealList.strMeal
            }
        }
    }
}