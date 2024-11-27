package com.example.plantsapp.views.plant.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.plantsapp.data.Garden
import com.example.plantsapp.R
import com.example.plantsapp.databinding.AdapterItemGardenMainBinding

class GardenItemsMainAdapter(
    private var gardens: List<Garden>,
    private var navController: NavController,
    private var context: Context
) : RecyclerView.Adapter<GardenItemsMainAdapter.GardenListViewHolder>() {
    inner class GardenListViewHolder(private var binding: AdapterItemGardenMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "ResourceAsColor")
        fun bind(garden: Garden,pos:Int) {
            binding.apply {
                tvGardenName.text = garden.name
                tvCountOfGardenPlants.text = garden.plants.size.toString()
                if(pos%3==0)
                {
                    imgGardenBackground.setImageResource(R.drawable.garden2)
                }else if(pos%3==1)
                {
                    imgGardenBackground.setImageResource(R.drawable.garden3)
                }else if(pos%3==2)
                {
                    imgGardenBackground.setImageResource(R.drawable.garden1)
                }
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
        holder.bind(gardens[position],position)
    }

}