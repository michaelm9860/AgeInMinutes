package com.example.dateinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvAgeInMins: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datePickBtn: Button = findViewById(R.id.datePickBtn)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMins = findViewById(R.id.tvAgeInMins)
        datePickBtn.setOnClickListener {
            datePicker()
        }
    }

    private fun datePicker(){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(this,
            {_, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth+1}/$selectedYear"
                tvSelectedDate?.text = selectedDate

                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val date = dateFormat.parse(selectedDate)
                date?.let {
                    val selectedDateInMins = date.time / 60_000

                    val currentDate = dateFormat.parse(dateFormat.format(System.currentTimeMillis()))


                    currentDate?.let {
                        val currentDateInMins = currentDate.time / 60_000

                        val differenceInMins = currentDateInMins - selectedDateInMins

                        tvAgeInMins?.text = differenceInMins.toString()
                    }

                }
            },
            year,
            month,
            day
        )

        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 86400000
        datePickerDialog.show()
    }
}