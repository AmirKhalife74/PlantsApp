package com.example.plantsapp.Views.Garden.gardenItem

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.example.plantsapp.R
import com.example.plantsapp.ViewModels.GardenViewModel
import com.example.plantsapp.Views.Plant.HomePlantFragmentArgs
import com.example.plantsapp.databinding.FragmentGardenBinding
import com.example.plantsapp.databinding.FragmentGardenItemBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class GardenItemFragment : Fragment() {

    private lateinit var binding: FragmentGardenItemBinding
    @Inject
    lateinit var gardenViewModel: GardenViewModel
    private var gardenId:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentGardenItemBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        listen()
        observe()
    }

    @SuppressLint("SetTextI18n")
    private fun init() {
        val args: GardenItemFragmentArgs by navArgs()
        gardenId = args.gardenId
        gardenViewModel.viewModelScope.launch {
            gardenViewModel.getGardenById(gardenId)
        }


    }
    private fun listen() {}
    private fun observe() {
        gardenViewModel.viewModelScope.launch {
            gardenViewModel.getGardenResponse.observe(viewLifecycleOwner){
                it?.let {
                    binding.apply {
                        tvGardenId.text = it.name
                    }
                }
            }
        }
    }
}