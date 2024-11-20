package com.example.plantsapp.Views.Plant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.plantsapp.Data.Plant
import com.example.plantsapp.R
import com.example.plantsapp.ViewModels.MainViewModel
import com.example.plantsapp.databinding.FragmentShopPlantBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShopPlantFragment : Fragment() {

    private lateinit var binding: FragmentShopPlantBinding
    private lateinit var plant: Plant

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopPlantBinding.inflate(inflater, container, false)
        return binding.root
    }


    private fun addToCartButtons(){
        binding.apply {
            val cart = viewModel.cart.value
            btnPlus.setOnClickListener {
                cart?.plants?.add(plant)
                tvPlantValue.text = ((tvPlantValue.text.toString().toInt()) + 1).toString()
                if (tvPlantValue.text.toString().toInt() >0)
                {
                    btnMinus.isEnabled = true
                }
                viewModel.cart.postValue(cart)
                totalCost()
            }

            btnMinus.setOnClickListener {
                cart?.plants?.remove(plant)
                tvPlantValue.text = ((tvPlantValue.text.toString().toInt()) - 1).toString()
                if (tvPlantValue.text.equals("0"))
                {
                    btnMinus.isEnabled = false
                }
                viewModel.cart.postValue(cart)
                totalCost()
            }
        }
    }

    private fun totalCost()
    {
        binding.apply {
            tvPlantPrice.text = "$" + (plant.price?.times(tvPlantValue.text.toString().toInt())).toString()
        }
    }




}