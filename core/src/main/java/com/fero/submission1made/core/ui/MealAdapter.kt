package com.fero.submission1made.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fero.submission1made.core.R
import com.fero.submission1made.core.databinding.ItemListMealBinding
import com.fero.submission1made.core.domain.model.Meal
import java.util.ArrayList

class MealAdapter : RecyclerView.Adapter<MealAdapter.ListViewHolder>() {

    private var listData = ArrayList<Meal>()
    var onItemClick: ((Meal) -> Unit)? = null

    fun setData(newListData: List<Meal>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_meal, parent, false))

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount() = listData.size

    inner class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMealBinding.bind(itemView)
        fun bind(data: Meal) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(ivItemImage)
                tvItemTitle.text = data.name
//                tvItemSubtitle.text = data.address
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}