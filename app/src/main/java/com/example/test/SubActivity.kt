package com.example.test

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import java.text.SimpleDateFormat
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

        val df = SimpleDateFormat("yyyyMMdd")
        //시작날짜 형변환
        val sd = df.parse("$year$month$day").time
        //현재 날짜 정보 받기
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


        val calendar = Calendar.getInstance()
        val year1 = calendar.get(Calendar.YEAR)
        val month1 = calendar.get(Calendar.MONTH)
        val dayOfMonth1 = calendar.get(Calendar.DAY_OF_MONTH)
        var date = Calendar.getInstance()
        var day1 = "$year1.$month1.$dayOfMonth1"

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
                when(position){
                    0 -> {  //오늘
                        dday1.text="$year1.$month1.$dayOfMonth1"
                    }
                    1 -> {  //처음만난날
                        dday1.text="$year. $month. $day."
                    }
                    2->{    //10일

                    }
                    3->{    //50일

                    }
                }
            }
        }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.actionmenu, menu)
        return true
    }

}