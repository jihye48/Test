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
        var year = intent.getStringExtra("year")!!.toString()
        var month = intent.getStringExtra("month")!!.toString()
        var day = intent.getStringExtra("day")!!.toString()

        tV1.text = "$year. $month. $day." //sd 출력

        val df = SimpleDateFormat("yyyyMMdd") //String 날짜 형식 정의
        //시작 날짜 형변환
        val sd = df.parse("$year$month$day").time
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
        val year1 = calendar.get(Calendar.YEAR)
        val month1 = calendar.get(Calendar.MONTH)
        val dayOfMonth1 = calendar.get(Calendar.DAY_OF_MONTH)
        val date: Date = Date()
        calendar.time = date

        // (res/values/array.xml)에 만들어둔 dday_list가져오기
        var ddayList = resources.getStringArray(R.array.dday_list)
        val myAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ddayList)
        var spinner1: Spinner = findViewById(R.id.spinner1)
        var dday1: TextView = findViewById(R.id.dday1)
        spinner1.adapter = myAdapter
        //spinner1 선택 리스너
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            //선택하지 않았을때
            override fun onNothingSelected(p0: AdapterView<*>?) {
                dday1.text = "선택해주세요"
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                var year = year.toInt()
                var month = month.toInt()
                var day = day.toInt()
                when (position) {
                    //오늘  (month가 1 작게 나와서 1 더함)
                    0 -> {
                        dday1.text = "$year1.${month1 + 1}. $dayOfMonth1"
                    }
                    //처음만난날
                    1 -> {
                        dday1.text = "$year. $month. $day."
                    }
                    //10일
                    2 -> {
                        var monthday=getten(day,month)
                        dday1.text="$year. $monthday"
                    }
                    //50일
                    3 -> {
                        var monthday=getfifty(day,month)
                        dday1.text="$year. $monthday"
                    }
                    //100일
                    4 -> {
                        var monthday=getfifty(day,month)
                    }
                    //1년
                    5 -> {
                        year += 1
                        dday1.text = "$year. $month. $day"
                        if ((year1 == year) && (month1 == month - 1) && (dayOfMonth1 == day))
                            Toast.makeText(this@SubActivity, "1주년을 축하합니다!", Toast.LENGTH_SHORT)
                                .show()
                    }
                }
            }
        }
        plusButton = findViewById(R.id.plusButton)
        plusButton.setOnClickListener {
            Toast.makeText(this@SubActivity, "fab버튼 클릭", Toast.LENGTH_SHORT).show()
            var intent=Intent(this,PopupActivity1::class.java)
            startActivity(intent)
        }


    }

    fun getten(day:Int, month: Int):String{
        var month=month
        var day=day
        day += 10
        when (month) {
            1, 3, 5, 7, 8, 10, 12 -> {
                if (day > 31) {
                    month += 1
                    day -= 31
                }
            }

            4, 6, 9, 11 -> {
                if (day > 30) {
                    month += 1
                    day -= 30
                }
            }

            2 -> {
                if (day > 28) {
                    month += 1
                    day -= 28
                }
            }
        }
        var strMonth=month.toString()
        var strDay=day.toString()
        return "$strMonth. $strDay"
    }

    fun getfifty(day:Int,month:Int):String {
        var month=month
        var day=day
        when (month) {
            1, 3, 5, 7, 8, 10, 12 -> {
                month+=1    //31일 더하기
                day+=19     //19일 더하기
                if (day > 31) {
                    month += 1
                    day -= 31
                }
            }

            4, 6, 9, 11 -> {
                month+=1    //30일 더하기
                day+=20     //20일 더하기
                if (day > 30) {
                    month += 1
                    day -= 30
                }
            }

            2 -> {
                month+=1    //28일 더하기
                day+=22     //22일 더하기
                if (day > 28) {
                    month += 1
                    day -= 28
                }
            }
        }
        var strMonth=month.toString()
        var strDay=day.toString()
        return "$strMonth. $strDay"
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
            R.id.action_brush->{
                val intent = Intent(this, brushActivity::class.java)
                startActivity(intent)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

}