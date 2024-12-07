package com.example.plantsapp.views.plant.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.plantsapp.data.Garden
import com.example.plantsapp.data.Plant
import com.example.plantsapp.R
import com.example.plantsapp.databinding.AdapterItemGardenMainBinding
import com.example.plantsapp.databinding.ItemPlantCardBinding

class PlanetOfGardenItemAdapter(
    private var garden: Garden,
    private var navController: NavController,
    private var context: Context
) : RecyclerView.Adapter<PlanetOfGardenItemAdapter.GardenListViewHolder>() {
    inner class GardenListViewHolder(private var binding: ItemPlantCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "ResourceAsColor")
        fun bind(plant: Plant, pos:Int) {
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
        holder.bind(garden.plants[position],position)
    }

}