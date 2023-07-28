package com.example.test

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class SubActivity : AppCompatActivity() {

    lateinit var plusButton: FloatingActionButton

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        setSupportActionBar(findViewById(R.id.main_tool_bar))

        val tV1 = findViewById<TextView>(R.id.startDay) //만난 날짜(sd) 텍스트 뷰
        //메인에서 imageBtn클릭할때 보낸 인텐트(만난 날짜) 받기
        var metYear = intent.getStringExtra("year")!!.toString()
        var metMonth = intent.getStringExtra("month")!!.toString()
        var metDay = intent.getStringExtra("day")!!.toString()

        tV1.text = "$metYear. $metMonth. $metDay." //sd 출력
        //Date->String
        val df = SimpleDateFormat("yyyyMMdd")
        //시작 날짜 형변환
        val sd = df.parse("$metYear$metMonth$metDay").time
        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time.time
        val tV2 = findViewById<TextView>(R.id.dayN) //함께한 지 n일 텍스트 뷰
        tV2.text = "${(today - sd) / (24 * 60 * 60 * 1000) + 1}일" //sd일부터 며칠 째인지 출력

        //현재 날짜 정보 받기
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH+1)
        val day= calendar.get(Calendar.DAY_OF_MONTH)
        val date = Date()
        calendar.time = date

        // (res/values/array.xml)에 만들어둔 dday_list가져오기
        var ddayList = resources.getStringArray(R.array.dday_list)
        val myAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ddayList)
        var spinner1: Spinner = findViewById(R.id.spinner1)
        var dday1: TextView = findViewById(R.id.dday1)
        spinner1.adapter = myAdapter

        //spinner1 선택 리스너
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                var intMetYear = metYear.toInt()
                var intMetMonth = metMonth.toInt()
                var intMetDay = metDay.toInt()

                var mathCalendar:Calendar= Calendar.getInstance()
                mathCalendar.set(intMetYear,intMetMonth,intMetDay)

                when (position) {

                    //오늘
                    0 -> {
                        dday1.text="$year. $month. $day"
                    }
                    //처음만난날
                    1 -> {
                        dday1.text="$intMetYear. $intMetMonth. $intMetDay"
                    }
                    //10일
                    2 -> {
                        mathCalendar.add(Calendar.DAY_OF_MONTH,10)
                        dday1.text="${mathCalendar.get(Calendar.YEAR)}.${mathCalendar.get(Calendar.MONTH)}.${mathCalendar.get(Calendar.DAY_OF_MONTH)}."
                    }
                    //50일
                    3 -> {
                        mathCalendar.add(Calendar.DAY_OF_MONTH,50)
                        dday1.text="${mathCalendar.get(Calendar.YEAR)}.${mathCalendar.get(Calendar.MONTH)}.${mathCalendar.get(Calendar.DAY_OF_MONTH)}."
                    }
                    //100일
                    4 -> {
                        mathCalendar.add(Calendar.DAY_OF_MONTH,100)
                        dday1.text="${mathCalendar.get(Calendar.YEAR)}.${mathCalendar.get(Calendar.MONTH)}.${mathCalendar.get(Calendar.DAY_OF_MONTH)}."
                    }
                    //1년
                    5 -> {
                        intMetYear+=1
                        dday1.text="$intMetYear. $intMetMonth. $intMetDay"
                        if((year==intMetYear)&&(month==intMetMonth)&&(day==intMetDay))
                            Toast.makeText(this@SubActivity, "1주년을 축하합니다!", Toast.LENGTH_SHORT).show()
                    }
                    //1000일
                    6 -> {
                        mathCalendar.add(Calendar.DAY_OF_MONTH,1000)
                        dday1.text="${mathCalendar.get(Calendar.YEAR)}.${mathCalendar.get(Calendar.MONTH)}.${mathCalendar.get(Calendar.DAY_OF_MONTH)}."
                        if ((year == mathCalendar.get(Calendar.YEAR)) && (month == mathCalendar.get(Calendar.MONTH)) && (day==mathCalendar.get(Calendar.DAY_OF_MONTH)))
                            Toast.makeText(this@SubActivity, "100일을 축하합니다!", Toast.LENGTH_SHORT).show()
                    }
                    //2000일
                    7 -> {
                        mathCalendar.add(Calendar.DAY_OF_MONTH,2000)
                        if ((year == mathCalendar.get(Calendar.YEAR)) && (month == mathCalendar.get(Calendar.MONTH)) && (day==mathCalendar.get(Calendar.DAY_OF_MONTH)))
                            Toast.makeText(this@SubActivity, "200일을 축하합니다!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        plusButton = findViewById(R.id.plusButton)
        plusButton.setOnClickListener {
            var intent=Intent(this,PopupActivity1::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.actionmenu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId){
            R.id.action_calendar->{
                val intent = Intent(this, calActivity::class.java)
                startActivity(intent)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}