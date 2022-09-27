package com.example.calender.presentation

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.calender.databinding.ActivitySetEventBinding
import com.example.calender.di.DaggerMainComponent
import com.example.calender.di.RoomModule
import com.example.calender.viewmodel.SetViewModel
import java.util.*
import javax.inject.Inject

class SetEventActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var binding: ActivitySetEventBinding

    @Inject
    lateinit var viewModeFactory: ViewModelProvider.Factory
    private lateinit var mainViewModel: SetViewModel
    var startTimeHrs = -1
    var endTimeHrs = -1
    var startTimeMin = -1
    var endTimeMin = -1
    var date = -1
    var month = -1
    var year = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerMainComponent.builder().roomModule(RoomModule(application)).build().inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivitySetEventBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setDateListner()
        setTimeListner()
        mainViewModel = ViewModelProvider(this, viewModeFactory)[SetViewModel::class.java]
        binding.submitValue.setOnClickListener {
            checkValidity()
        }

        setObServer()

    }


    private fun setObServer() {
        mainViewModel.status.observe(this) {
            if (it.equals("Success"))
                finish()
            else
                Toast.makeText(this, "Time Already filled", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkValidity() {
        if (startTimeHrs == -1 || endTimeHrs == -1 || startTimeMin == -1 || endTimeMin == -1 || date == -1 || month == -1 || year == -1) {
            Toast.makeText(this, "Please fill all the data", Toast.LENGTH_SHORT).show()
        } else {
            if (Date().time > Date(
                    year,
                    month,
                    date,
                    startTimeHrs,
                    startTimeMin
                ).time || Date().time > Date(
                    year,
                    month,
                    date,
                    endTimeHrs,
                    endTimeMin
                ).time || Date(year, month, date, startTimeHrs, startTimeMin).time > Date(
                    year,
                    month,
                    date,
                    endTimeHrs,
                    endTimeMin
                ).time
            ) {
                Toast.makeText(this, "Enter valid time", Toast.LENGTH_SHORT).show()
            } else {
                mainViewModel.insertMeetingDetail(
                    binding.meetingNameValue.text.toString(),
                    Date(year, month, date, startTimeHrs, startTimeMin - 1900),
                    Date(year, month, date, endTimeHrs, endTimeMin - 1900)
                )
            }

        }

    }

    private fun setTimeListner() {
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime[Calendar.HOUR_OF_DAY]
        val minute = mcurrentTime[Calendar.MINUTE]
        binding.startValue.setOnClickListener {
            val mTimePicker = TimePickerDialog(
                this,
                { timePicker, selectedHour, selectedMinute ->
                    this.startTimeHrs = selectedHour
                    this.startTimeMin = selectedMinute
                    binding.startValue.setText("$selectedHour:$selectedMinute")
                },
                hour,
                minute,
                false
            ) //Yes 24 hour time

            mTimePicker.setTitle("Select Time")
            mTimePicker.show()
        }

        binding.endValue.setOnClickListener {
            val mTimePicker = TimePickerDialog(
                this,
                { timePicker, selectedHour, selectedMinute ->
                    this.endTimeHrs = selectedHour
                    this.endTimeMin = selectedMinute
                    binding.endValue.setText("$selectedHour:$selectedMinute")
                },
                hour,
                minute,
                false
            ) //Yes 24 hour time

            mTimePicker.setTitle("Select Time")
            mTimePicker.show()
        }
    }

    private fun setDateListner() {
        binding.setDate.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance(TimeZone.getDefault())

            val dialog = DatePickerDialog(
                this, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            dialog.datePicker.minDate = System.currentTimeMillis()
            dialog.show()
        }
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        this.date = day
        this.month = month
        this.year = year
        binding.setDate.text = "$day/$month/$year"
    }


}