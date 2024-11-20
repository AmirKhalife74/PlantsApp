package com.example.plantsapp.Views.Plant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.plantsapp.Data.Garden
import com.example.plantsapp.Data.Plant
import com.example.plantsapp.Utils.addToGarden
import com.example.plantsapp.ViewModels.GardenViewModel
import com.example.plantsapp.ViewModels.MainViewModel
import com.example.plantsapp.databinding.FragmentPlantBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomePlantFragment : Fragment() {

    private lateinit var binding: FragmentPlantBinding
    private lateinit var plant: Plant

    @Inject
    lateinit var gardenViewModel: GardenViewModel

    @Inject
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlantBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        listen()
        observe()
    }

    private fun observe() {
        getGardenListObserve()
    }

    private fun init() {
        val args: HomePlantFragmentArgs by navArgs()
        plant = args.plant
        setPlant()
        setRc()
    }

    private fun listen() {
        goBackButton()
        addToGardenButton()

    }

    private fun addToGardenButton() {
        binding.apply {
            btnAddToGarden.setOnClickListener {
                gardenViewModel.viewModelScope.launch {
                    gardenViewModel.getAllGardens()
                }

            }
        }
    }

    private fun goBackButton() {
        binding.apply {
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun getGardenListObserve() {
        gardenViewModel.getAllGardensResponse.observe(viewLifecycleOwner) { data ->
            data?.let {
                val addNewGardenItem = Garden(id = 0,"addNewItem", mutableListOf())
                it.add(0,addNewGardenItem)
                addToGarden(it,plant){ type,garden,gardenName ->
                    gardenViewModel.viewModelScope.launch {
                        when(type)
                        {
                            0->{
                                gardenViewModel.createGarden(gardenName)
                            }
                            1->{
                                gardenViewModel.addPlantToGarden(garden)
                            }

                        }

                    }

                }
            }
        }
    }

    private fun setPlant() {
        binding.apply {
            tvPlantName.text = plant.name
            tvPlantDesc.text = plant.desc.toString()
            tvPlantPot.text = plant.pot.toString()
            tvPlantHieght.text = plant.height.toString()
        }

    }

    private fun setRc() {
        binding.apply {

        }
    }

}