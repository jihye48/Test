package com.example.test

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
        loadData()
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
                var date=year.toString()+month.toString()+day.toString()
                var things=edtText.text.toString()
                saveData(things,date)
            }

        }

        //취소 버튼을 눌렀을 때
        noButton.setOnClickListener{
            finish()
        }

    }

    private fun saveData(date:String,things:String){
        val pref=this.getPreferences(0)
        val editor=pref.edit()

        editor.putString("date",date).putString("things",things)
        Toast.makeText(this,"기념일이 저장되었습니다",Toast.LENGTH_SHORT).show()
        finish()
        loadData()
    }

    private fun loadData(){

        var celebrate=findViewById<TextView>(R.id.celebrate)
        var celebratethings=findViewById<TextView>(R.id.celabratethings)
        val pref=this.getPreferences(0)
        val date=pref.getString("date",null)
        val things=pref.getString("date",null)

        if(date!=null&&things!=null){

            var calendar: Calendar = Calendar.getInstance()
            var parse_date: Date ?=null
            var stringtodate:SimpleDateFormat = SimpleDateFormat("yyyyMMdd")

            try{
                parse_date=stringtodate.parse(date)
                calendar.time=parse_date
            }catch(e:Exception){}

            var year=calendar.get(Calendar.YEAR)
            var month=calendar.get(Calendar.MONTH)
            var day=calendar.get(Calendar.DAY_OF_MONTH)

            celebrate.text="$year. $month. $day"
            celebratethings.text="$things"
        }
    }

}