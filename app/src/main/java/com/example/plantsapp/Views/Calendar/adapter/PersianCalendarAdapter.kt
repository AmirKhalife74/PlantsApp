package com.example.plantsapp.Views.Calendar.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plantsapp.R

class PersianCalendarAdapter(private val days: List<String>) :
    RecyclerView.Adapter<PersianCalendarAdapter.DayViewHolder>() {

    inner class DayViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dayText: TextView = view.findViewById(R.id.day_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.calendar_item, parent, false)
        return DayViewHolder(view)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.dayText.text = days[position]
    }

    override fun getItemCount() = days.size
}