package com.example.test

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class SubActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val tV1 = findViewById<TextView>(R.id.startDay) //시작날짜텍스트뷰
        //메인에서 imgBtn클릭할때 보낸 인텐트 받기
        var year = intent.getStringExtra("year")!!.toString()
        var month = intent.getStringExtra("month")!!.toString()
        var day = intent.getStringExtra("day")!!.toString()

        tV1.text = "$year. $month. $day."

        val df = SimpleDateFormat("yyyyMMdd")
        val sd = df.parse("$year$month$day").time
        val today = Calendar.getInstance().apply{
            set(Calendar.HOUR_OF_DAY,0)
            set(Calendar.MINUTE,0)
            set(Calendar.SECOND,0)
            set(Calendar.MILLISECOND,0)
        }.time.time

        val tV2 = findViewById<TextView>(R.id.dayN)
        //함께한지 n일 텍스트뷰
        tV2.text = "${(today - sd) / (24 * 60 * 60 * 1000) + 1}일"





//        val calendar = Calendar.getInstance()
//        val year1 = calendar.get(Calendar.YEAR)
//        val month1 = calendar.get(Calendar.MONTH)
//        val dayOfMonth1 = calendar.get(Calendar.DAY_OF_MONTH)
//        var today = Calendar.getInstance()
//        var day1 = "$year-$month-$day 00:00:00"
//        var day2 =


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.actionmenu, menu)
        return true
    }

}