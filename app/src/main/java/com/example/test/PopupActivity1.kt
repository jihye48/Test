package com.example.test

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

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

        var year=datePicker.year
        var month=datePicker.month+1
        var day=datePicker.dayOfMonth

        datePicker.setOnDateChangedListener { datePicker, year, month, day ->

        }

        //취소 버튼을 눌렀을 때
        noButton.setOnClickListener{
            finish()
        }

    }
}