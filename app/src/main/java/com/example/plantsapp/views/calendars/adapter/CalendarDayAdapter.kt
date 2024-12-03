import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.plantsapp.R

class CalendarDayAdapter(
    private val context: Context,
    private val days: List<String>,
    private val wateringReminders: List<String>
) : RecyclerView.Adapter<CalendarDayAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dayText: TextView = view.findViewById(R.id.tv_day)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_dey, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val day = days[position]

        holder.dayText.text = day

        // Highlight days with reminders
        if (wateringReminders.contains(day)) {
            holder.dayText.setBackgroundColor(ContextCompat.getColor(context, R.color.reminder_day))
        } else {
            holder.dayText.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
        }
    }

    override fun getItemCount(): Int = days.size
}
