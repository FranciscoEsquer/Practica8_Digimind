package esquer.francisco.mydigimind.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.provider.CalendarContract.CalendarEntity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import esquer.francisco.mydigimind.R
import esquer.francisco.mydigimind.databinding.FragmentDashboardBinding
import esquer.francisco.mydigimind.ui.Task
import esquer.francisco.mydigimind.ui.home.HomeFragment
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val time: Button = root.findViewById(R.id.time)
        time.setOnClickListener{
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY,hour)
                cal.set(Calendar.MINUTE, minute)

                time.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(root.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        val done: Button = root.findViewById(R.id.done)
        val title: EditText = root.findViewById(R.id.name)
        val monday: CheckBox = root.findViewById(R.id.monday)
        val tuesday: CheckBox = root.findViewById(R.id.tuesday)
        val wednesday: CheckBox = root.findViewById(R.id.wednesday)
        val thursday: CheckBox = root.findViewById(R.id.thursday)
        val friday: CheckBox = root.findViewById(R.id.friday)
        val saturday: CheckBox = root.findViewById(R.id.saturday)
        val sunday: CheckBox = root.findViewById(R.id.sunday)

        done.setOnClickListener{
            var titulo = title.text.toString()
            var tiempo = time.text.toString()
            var days = ArrayList<String>()
            if (monday.isChecked)
                days.add("Monday")
            if (tuesday.isChecked)
                days.add("Tuesday")
            if (wednesday.isChecked)
                days.add("Wednesday")
            if (thursday.isChecked)
                days.add("Thursday")
            if (friday.isChecked)
                days.add("Friday")
            if (saturday.isChecked)
                days.add("Saturday")
            if (sunday.isChecked)
                days.add("Sunday")

            var task = Task(titulo,days,tiempo)
            HomeFragment.tasks.add(task)
            Toast.makeText(root.context, "New task added!", Toast.LENGTH_SHORT).show()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}