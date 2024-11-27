package com.example.plantsapp.views.garden

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.plantsapp.data.Garden
import com.example.plantsapp.utils.RecyclerTouchListener
import com.example.plantsapp.viewModels.GardenViewModel
import com.example.plantsapp.views.plant.adapter.GardenItemsMainAdapter
import com.example.plantsapp.views.plant.adapter.PlanetOfGardenItemAdapter
import com.example.plantsapp.databinding.FragmentGardenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class GardenListFragment : Fragment() {

    private lateinit var binding: FragmentGardenBinding

    @Inject
    lateinit var gardenViewModel: GardenViewModel

    lateinit var gardenList: List<Garden>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGardenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        listen()
        observe()
    }

    private fun init() {
        gardenViewModel.viewModelScope.launch {
            gardenViewModel.getAllGardens()
        }
//        setCalendar()

    }

    private fun listen() {

    }

    private fun observe() {
        binding.apply {
            gardenViewModel.viewModelScope.launch {
                gardenViewModel.getAllGardensResponse.observe(viewLifecycleOwner) { it ->
                    it?.let {
                        if (it.isNotEmpty()) {
                            gardenList = it
                            val adapter = GardenItemsMainAdapter(
                                context = requireContext(),
                                gardens = it,
                                navController = findNavController()
                            )
                            rcGardens.adapter = adapter
                            rcGardens.addOnItemTouchListener(
                                RecyclerTouchListener(
                                    context,
                                    rcGardens,
                                    object : RecyclerTouchListener.OnItemClickListener {
                                        override fun onItemClick(view: View?, position: Int) {
                                            gardenViewModel.viewModelScope.launch {
                                                gardenViewModel.getGardenById(gardenList[position].id)
                                            }
                                            prgrsGardenPlants.visibility = View.VISIBLE

                                        }

                                        override fun onLongItemClick(view: View?, position: Int) {

                                        }
                                    })
                            )

                        }
                    }
                }

                gardenViewModel.getGardenResponse.observe(viewLifecycleOwner)
                {
                    it?.let {
                        prgrsGardenPlants.visibility = View.GONE
                        val adapter = PlanetOfGardenItemAdapter(
                            context = requireContext(),
                            garden = it,
                            navController = findNavController())
                        rcGardenPlants.adapter = adapter
                        rcGardenPlants.setHasFixedSize(true)


                    }
                }
            }
        }

    }

//    private fun setCalendar() {
//        binding.apply {
//            val currentYear = 1402 // Example Persian year
//            val currentMonth = 6   // Example Persian month (Aban)
//
//            // Get days for the current month
//            val daysInMonth = DateUtil.getDaysInMonth(currentYear, currentMonth)
//            val daysList = (1..daysInMonth).map { it.toString() }
//
//            rcCalendar.layoutManager =
//                GridLayoutManager(requireContext(), 7) // 7 columns for days of the week
//            rcCalendar.adapter = PersianCalendarAdapter(daysList)
//        }
//
//    }

}