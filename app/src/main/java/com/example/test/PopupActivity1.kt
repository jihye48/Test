package com.example.test

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class PopupActivity1 : AppCompatActivity() {

    lateinit var noButton: Button
    lateinit var saveButton:Button
    lateinit var datePicker: DatePicker
    lateinit var edtText:EditText

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup1)

        noButton=findViewById(R.id.noButton)
        saveButton=findViewById(R.id.saveButton)
        datePicker=findViewById(R.id.datepicker)
        edtText=findViewById(R.id.writeEdit)

        datePicker.setOnDateChangedListener { datePicker, year, month, day ->
            //저장버튼을 눌렀을 때
            saveButton.setOnClickListener {
                var year=datePicker.year
                var month=datePicker.month+1
                var day=datePicker.dayOfMonth
                //var date=year.toString()+month.toString()+day.toString()
                var things=edtText.text.toString()
                //saveData(things,date)
                intent=Intent(this,SubActivity::class.java).apply{
                    putExtra("year",year.toString())
                    putExtra("month",month.toString())
                    putExtra("day",day.toString())
                    putExtra("things",things)
                }
                setResult(RESULT_OK)
                if(!isFinishing)
                    finish()
            }

        }

        //취소 버튼을 눌렀을 때
        noButton.setOnClickListener{
            finish()
        }

    }

}