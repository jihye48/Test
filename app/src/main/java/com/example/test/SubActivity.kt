package com.example.test

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class SubActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
        //시작날짜텍스트뷰
        val tV1 = findViewById<TextView>(R.id.startDay)
        //메인에서 imgBtn클릭할때 보낸 인텐트(만난 날짜) 받기
        var year = intent.getStringExtra("year")!!.toString()
        var month = intent.getStringExtra("month")!!.toString()
        var day = intent.getStringExtra("day")!!.toString()
        //startDay텍스트 지정
        tV1.text = "$year. $month. $day."
        //String 날짜 형식 정의
        val df = SimpleDateFormat("yyyyMMdd")
        //시작날짜 형변환
        val sd = df.parse("$year$month$day").time
        val today = Calendar.getInstance().apply{
            set(Calendar.HOUR_OF_DAY,0)
            set(Calendar.MINUTE,0)
            set(Calendar.SECOND,0)
            set(Calendar.MILLISECOND,0)
        }.time.time

        //함께한지 n일 텍스트뷰
        val tV2 = findViewById<TextView>(R.id.dayN)
        //현재날짜-시작날짜
        tV2.text = "${(today - sd) / (24 * 60 * 60 * 1000) + 1}일"

        //현재 날짜 정보 받기
        val calendar = Calendar.getInstance()
        val year1 = calendar.get(Calendar.YEAR)
        val month1 = calendar.get(Calendar.MONTH)
        val dayOfMonth1 = calendar.get(Calendar.DAY_OF_MONTH)
        val date:Date=Date()
        calendar.time=date

        // (res/values/array.xml)에 만들어둔 dday_list가져오기
        var ddayList=resources.getStringArray(R.array.dday_list)
        val myAdapter=ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,ddayList)
        var spinner1:Spinner=findViewById(R.id.spinner1)
        var dday1:TextView=findViewById(R.id.dday1)
        spinner1.adapter=myAdapter
        //spinner1 선택 리스너
        spinner1.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            //선택하지 않았을때
            override fun onNothingSelected(p0: AdapterView<*>?) {
                dday1.text="선택해주세요"
            }
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                var year=year.toInt()
                var month=month.toInt()
                var day=day.toInt()
                when(position){
                    0 -> {  //오늘  (month가 1 작게 나와서 1 더함)
                        dday1.text="$year1.${month1+1}. $dayOfMonth1"

                    }
                    1 -> {  //처음만난날
                        dday1.text="$year. $month. $day."
                    }
                    2->{    //10일
                        day+=10
                        when(month){
                            1,3,5,7,8,10,12 -> {
                                if(day>31){
                                    month+=1
                                    day-=31
                                }
                            }
                            2,4,6,9,11 -> {
                                if(day>30){
                                    month+=1
                                    day-=30
                                }
                            }
                            2->{
                                if(day>28){
                                    month+=1
                                    day-=28
                                }
                            }
                        }
                        dday1.text="$year. $month. $day"
                    }
                    3->{    //50일
                        dday1.text="$year. $month. $day"
                    }
                    4->{    //100일

                    }
                    5->{    //1년
                        year+=1
                        dday1.text="$year. $month. $day"
                        if((year1==year)&&(month1==month)&&(dayOfMonth1==day))
                            Toast.makeText(this@SubActivity, "1주년을 축하합니다!",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
    /*현재 날짜로부터 더하기
    fun afterDate(date: String, day: Int, pattern: String = "yyyy. MM. dd"): String {
        val format = SimpleDateFormat(pattern, Locale.getDefault())

        val calendar = Calendar.getInstance()
        format.parse(date)?.let { calendar.time = it }
        calendar.add(Calendar.DAY_OF_YEAR, day)

        return format.format(calendar.time)
    }*/

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.actionmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.action_calendar->{

            }
            R.id.action_brush->{

            }
        }
        return super.onOptionsItemSelected(item)
    }



}