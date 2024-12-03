package com.example.plantsapp.views.garden

import CalendarDayAdapter
import JalaliDate
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.plantsapp.data.Garden
import com.example.plantsapp.databinding.FragmentCalendarBinding
import com.example.plantsapp.utils.RecyclerTouchListener
import com.example.plantsapp.viewModels.GardenViewModel
import com.example.plantsapp.views.plant.adapter.GardenItemsMainAdapter
import com.example.plantsapp.views.plant.adapter.PlanetOfGardenItemAdapter
import com.example.plantsapp.databinding.FragmentGardenBinding
import com.example.plantsapp.viewModels.ReminderViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class GardenListFragment : Fragment() {

    private lateinit var binding: FragmentGardenBinding

    @Inject
    lateinit var gardenViewModel: GardenViewModel

    @Inject
    lateinit var reminderViewModel: ReminderViewModel

    private val jalaliDate = JalaliDate()
    private var currentYear = 1403
    private var currentMonth = 9

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
                        val adapter = PlanetOfGardenItemAdapter(
                            context = requireContext(),
                            garden = it,
                            navController = findNavController()
                        )
                    }
                }
            }
        }

    }

    private fun setCalendar()
    {
        binding.apply {
            updateCalendar()
            // Previous/Next month navigation
            btnPrevious.setOnClickListener {
                currentMonth--
                if (currentMonth < 1) {
                    currentMonth = 12
                    currentYear--
                }
                updateCalendar()
            }

            btnNext.setOnClickListener {
                currentMonth++
                if (currentMonth > 12) {
                    currentMonth = 1
                    currentYear++
                }
                updateCalendar()
            }
        }
    }

    private fun updateCalendar() {
        binding.apply {


            val daysInMonth = jalaliDate.getDaysInJalaliMonth(currentYear, currentMonth)
            val startDayOfWeek = jalaliDate.getStartDayOfMonth(currentYear, currentMonth)

            val days = mutableListOf<String>()
            val wateringReminders = mutableListOf<String>() // Store reminder dates

            for (i in 1 until startDayOfWeek) days.add("") // Empty spaces
            for (day in 1..daysInMonth) {
                val date = "$currentYear-${String.format("%02d", currentMonth)}-${
                    String.format(
                        "%02d",
                        day
                    )
                }"
                days.add(date)

                // Fetch watering reminders for this date
                reminderViewModel.viewModelScope.launch {
                    val reminders = reminderViewModel.getWateringReminders(date)
                    if (reminders.isNotEmpty()) {
                        wateringReminders.add(date)
                    }
                }

            }

            calendarGrid.layoutManager = GridLayoutManager(requireContext(), 7)
            calendarGrid.adapter = CalendarDayAdapter(requireContext(), days, wateringReminders)

            tvMonthYear.text = "${jalaliDate.getMonthName(currentMonth)} $currentYear"
        }

        // Fetch watering reminders for the given date

    }

}