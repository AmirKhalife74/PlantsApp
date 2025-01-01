package com.example.plantsapp.views.calendars

import com.example.plantsapp.views.calendars.adapter.CalendarDayAdapter
import com.example.plantsapp.utils.JalaliDate
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.plantsapp.databinding.FragmentCalendarBinding
import com.example.plantsapp.viewModels.ReminderViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CalendarFragment : Fragment() {

    @Inject
    lateinit var reminderViewModel:ReminderViewModel

    lateinit var binding: FragmentCalendarBinding
    private val jalaliDate = JalaliDate()
    private var currentYear = 1403
    private var currentMonth = 9

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalendarBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCalendar()

//        val reminder = WateringReminderModel(
//            plantId = plant.id,
//            gardenId = garden.id,
//            reminderDate = "1403-09-10" // This date should be in Jalali format
//        )
//        wateringReminderRepository.insertReminder(reminder)
    }

    @SuppressLint("DefaultLocale", "SetTextI18n")
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

}