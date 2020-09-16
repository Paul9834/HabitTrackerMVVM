package com.example.habittrackermvvm.ui.fragments.createhabit

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import java.util.*

import com.example.habittrackermvvm.R
import com.example.habittrackermvvm.data.models.Habit
import com.example.habittrackermvvm.ui.viewmodels.HabitsViewModel
import com.example.habittrackermvvm.utils.Calculations
import kotlinx.android.synthetic.main.fragment_create_habit.*


class CreateHabitFragment : Fragment(R.layout.fragment_create_habit), TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{


    private var title = ""
    private var description = ""
    private var drawableSelected = 0
    private var timesStamp = ""

    private var day = 0
    private var month = 0
    private var year = 0
    private var hour = 0
    private var minute = 0

    private var cleanDate = ""
    private var cleanTime = ""

    private lateinit var habitsViewModel: HabitsViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        habitsViewModel = ViewModelProvider(this).get(HabitsViewModel::class.java)

        btn_confirm.setOnClickListener {
            addHabitToDB()
        }

        pickDateAndTime()

        drawableSelected()

    }

    private fun addHabitToDB() {

        title = et_habitTitle.text.toString()
        description = et_habitDescription.text.toString()
        timesStamp = "$cleanDate $cleanTime"


        if (!(title.isEmpty() || description.isEmpty() || timesStamp.isEmpty() || drawableSelected == 0)) {

            val habit = Habit(0, title, description, timesStamp, drawableSelected)
            habitsViewModel.addHabit(habit)


            Toast.makeText(context, "Habit created success", Toast.LENGTH_SHORT).show()


            findNavController().navigate(R.id.action_createHabitFragment_to_habitListFragment)

        } else {
            Toast.makeText(context, "Please check the inputs", Toast.LENGTH_SHORT).show()
        }

    }


    private fun drawableSelected() {

        iv_fastFoodSelected.setOnClickListener {
            iv_fastFoodSelected.isSelected = !iv_fastFoodSelected.isSelected
            drawableSelected = R.drawable.fastfood_selected

            iv_smokingSelected.isSelected = false
            iv_teaSelected.isSelected = false
        }

        iv_smokingSelected.setOnClickListener {
            iv_smokingSelected.isSelected = !iv_smokingSelected.isSelected
            drawableSelected = R.drawable.ic_smoke_base

            iv_teaSelected.isSelected = false
            iv_fastFoodSelected.isSelected = false

        }

        iv_teaSelected.setOnClickListener {
            iv_teaSelected.isSelected = !iv_teaSelected.isSelected
            drawableSelected = R.drawable.ic_coffe_base


            iv_smokingSelected.isSelected = false
            iv_fastFoodSelected.isSelected = false

        }

    }


    private fun  pickDateAndTime() {
        btn_pickDate.setOnClickListener {
            getDateCalendar()
            DatePickerDialog(requireContext(), this, year, month, day).show()
        }
        btn_pickTime.setOnClickListener {
            getTimeCalendar()
            TimePickerDialog(context, this, hour, minute, true).show()
        }
    }


    private fun getTimeCalendar () {
        val cal = Calendar.getInstance()
        hour = cal.get(Calendar.HOUR_OF_DAY)
        minute = cal.get(Calendar.MINUTE)
    }


    private fun getDateCalendar()  {
        val cal = Calendar.getInstance()

        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        cleanTime = Calculations.cleanTime(hourOfDay, minute)
        tv_timeSelected.text = "Time: $cleanTime"
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        cleanDate = Calculations.cleanDate(dayOfMonth, month, year)
        tv_dateSelected.text = "Date: $cleanDate"
    }


}