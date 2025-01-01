package com.example.plantsapp.views.garden.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.plantsapp.data.Garden
import com.example.plantsapp.data.Plant
import com.example.plantsapp.R
import com.example.plantsapp.databinding.ItemPlantCardBinding

class PlanetOfGardenItemAdapter(
    private var garden: Garden,
) : RecyclerView.Adapter<PlanetOfGardenItemAdapter.GardenListViewHolder>() {
    inner class GardenListViewHolder(private var binding: ItemPlantCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "ResourceAsColor")
        fun bind(plant: Plant) {
            binding.apply {
                imgPlantImage.setImageResource(R.drawable.alovera)
                tvPlantName.text = plant.name
                tvPlantDescCard.text = plant.description
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GardenListViewHolder {
        val viewHolder = GardenListViewHolder(
            ItemPlantCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.setIsRecyclable(false)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return garden.plants.size
    }

    override fun onBindViewHolder(holder: GardenListViewHolder, position: Int) {
        holder.bind(garden.plants[position])
    }

}