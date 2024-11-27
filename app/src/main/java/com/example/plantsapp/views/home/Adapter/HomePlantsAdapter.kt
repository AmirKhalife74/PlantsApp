package com.example.plantsapp.views.home.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.plantsapp.data.Plant
import com.example.plantsapp.R
import com.example.plantsapp.views.home.HomeFragmentDirections
import com.example.plantsapp.databinding.AdapterPlantsHeaderBinding
import com.example.plantsapp.databinding.AdapterPlantsListBinding

class HomePlantsAdapter(
    private val items: MutableList<Plant?>,
    private val navController: NavController,
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_ITEM = 1
    }

    lateinit var itemBinding: AdapterPlantsListBinding
    lateinit var headerBinding: AdapterPlantsHeaderBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_HEADER) {
            headerBinding = AdapterPlantsHeaderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            HeaderViewHolder(headerBinding.root)
        } else {
            itemBinding =
                AdapterPlantsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ItemViewHolder(itemBinding.root)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {

            headerBinding.apply {
                headerTextView.text = "found${items.size}\n results"
            }
        } else if (holder is ItemViewHolder) {
            val item = items[position - 1]
            itemBinding.apply {
                //Glide.with(context).load("https://goo.gl/gEgYUd").into(imgPlant);
                imgPlant.setImageResource(R.drawable.alovera)
                tvPlantName.text = item?.name
                tvPlantPrice.text = "${item?.price}$"
                cardPlantItem.setOnClickListener {
                    if (item != null) {
                        val action = HomeFragmentDirections.actionHomeFragmentToPlantFragment(item)
                        navController.navigate(action)
                    }


                }

            }
        } else {

        }
    }

    override fun getItemCount(): Int {
        return items.size + 1
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            VIEW_TYPE_HEADER
        } else {
            VIEW_TYPE_ITEM
        }
    }
}