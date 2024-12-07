package com.example.plantsapp.views.garden.gardenItem

import CalendarDayAdapter
import JalaliDate
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.plantsapp.viewModels.GardenViewModel
import com.example.plantsapp.databinding.FragmentGardenItemBinding
import com.example.plantsapp.viewModels.ReminderViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class GardenItemFragment : Fragment() {


    private val jalaliDate = JalaliDate()
    private var currentYear = 1403
    private var currentMonth = 9

    private lateinit var binding: FragmentGardenItemBinding
    @Inject
    lateinit var gardenViewModel: GardenViewModel

    @Inject
    lateinit var reminderViewModel: ReminderViewModel

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

            }
        }
    }

    private fun setCalendar()
    {
        binding.apply {
            updateCalendar()
            // Previous/Next month navigation
            btnNext.setOnClickListener {
                currentMonth--
                if (currentMonth < 1) {
                    currentMonth = 12
                    currentYear--
                }
                updateCalendar()
            }

            btnPrevious.setOnClickListener {
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