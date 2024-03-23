package com.example.easymeals.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easymeals.pojo.PMeal
import com.example.easymeals.databinding.ItemPopularBinding

class AdapterPopularMeals : RecyclerView.Adapter<AdapterPopularMeals.Holder>() {
    var mealList: List<PMeal>? = null
    private lateinit var onClick: (Int) -> Unit?
    fun setOnClick(onClick: (Int) -> Unit) {
        this.onClick = onClick}

    private lateinit var onLongClick: (Int) -> Unit?
    fun setOnLongClick(onLongClick: (Int) -> Unit) {
        this.onLongClick = onLongClick}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return mealList?.size ?: 0
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = mealList!![position]
        holder.bind(data)
    }

    inner class Holder(val binding: ItemPopularBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onClick.invoke(mealList!![layoutPosition].idMeal.toInt())
            }
            binding.root.setOnLongClickListener{
                onLongClick.invoke(mealList!![layoutPosition].idMeal.toInt())
                true
            }
        }

        fun bind(mealList: PMeal) {
            Glide.with(binding.root.context)
                .load(mealList.strMealThumb)
                .into(binding.imgMeal)
        }
    }
}