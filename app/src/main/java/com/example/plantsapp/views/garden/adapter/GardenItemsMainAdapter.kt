package com.example.plantsapp.views.garden.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.plantsapp.R
import com.example.plantsapp.data.Garden
import com.example.plantsapp.databinding.AdapterItemGardenMainBinding

class GardenItemsMainAdapter(
    private var gardens: List<Garden>,
    private var navController: NavController,
    private var context: Context
) : RecyclerView.Adapter<GardenItemsMainAdapter.GardenListViewHolder>() {
    inner class GardenListViewHolder(private var binding: AdapterItemGardenMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "ResourceAsColor")
        fun bind(garden: Garden, pos: Int) {
            binding.apply {
                tvGardenName.text = garden.name
                tvGardenInfo.text = garden.details
                tvGardenCount.text = garden.count
                val resolvedColor = ContextCompat.getColor(context, garden.color)
                lytGardenColor.setBackgroundColor(resolvedColor)

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GardenListViewHolder {
        val viewHolder = GardenListViewHolder(
            AdapterItemGardenMainBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.setIsRecyclable(false)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return gardens.size
    }

    override fun onBindViewHolder(holder: GardenListViewHolder, position: Int) {
        holder.bind(gardens[position], position)
    }

}