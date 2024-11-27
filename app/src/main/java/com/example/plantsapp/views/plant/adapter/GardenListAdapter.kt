package com.example.plantsapp.views.plant.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.plantsapp.data.Garden
import com.example.plantsapp.R.color.main
import com.example.plantsapp.R.color.white
import com.example.plantsapp.databinding.AdapterItemGardenBinding

class GardenListAdapter(
    private var gardens: List<Garden>,
    private var context: Context
) : RecyclerView.Adapter<GardenListAdapter.GardenListViewHolder>() {
    inner class GardenListViewHolder(private var binding: AdapterItemGardenBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "ResourceAsColor")
        fun bind(garden: Garden) {
            binding.apply {
                if (garden.name == "addNewItem" && garden.id == 0 && garden.plants.isEmpty())
                {
                    crdGarden.setBackgroundColor(ContextCompat.getColor(context, main))
                    tvRowGarden.text = "+"
                    tvGardenName.text = garden.name
                    tvGardenName.setTextColor(ContextCompat.getColor(context, white))
                    tvRowGarden.setTextColor(ContextCompat.getColor(context, white))
                }else
                {
                    tvRowGarden.text = (adapterPosition + 1).toString()
                    tvGardenName.text = garden.name
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GardenListViewHolder {
        val viewHolder = GardenListViewHolder(
            AdapterItemGardenBinding.inflate(
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
        holder.bind(gardens[position])
    }

}