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
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class SubActivity : AppCompatActivity() {

    lateinit var plusButton: FloatingActionButton
    lateinit var activityResultLauncher:ActivityResultLauncher<Intent>

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

        setResultNext()
        plusButton = findViewById(R.id.plusButton)
        plusButton.setOnClickListener {
            var intent=Intent(this,PopupActivity1::class.java)
            activityResultLauncher.launch(intent)
        }

        // (res/values/array.xml)에 만들어둔 dday_list가져오기
        var ddayList = resources.getStringArray(R.array.dday_list)
        val myAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ddayList)
        var spinner1: Spinner = findViewById(R.id.spinner1)
        var spinner2:Spinner=findViewById(R.id.spinner2)
        var spinner3:Spinner=findViewById(R.id.spinner3)
        var dday1: TextView = findViewById(R.id.dday1)
        var dday2: TextView = findViewById(R.id.dday2)
        var dday3: TextView = findViewById(R.id.dday3)
        spinner1.adapter = myAdapter
        spinner2.adapter = myAdapter
        spinner3.adapter = myAdapter

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

                    //처음만난날
                    0 -> {
                        dday1.text="$intMetYear. $intMetMonth. $intMetDay"
                    }
                    //10일
                    1 -> {
                        mathCalendar.add(Calendar.DAY_OF_MONTH,10)
                        dday1.text="${mathCalendar.get(Calendar.YEAR)}.${mathCalendar.get(Calendar.MONTH)}.${mathCalendar.get(Calendar.DAY_OF_MONTH)}."
                    }
                    //50일
                    2 -> {
                        mathCalendar.add(Calendar.DAY_OF_MONTH,50)
                        dday1.text="${mathCalendar.get(Calendar.YEAR)}.${mathCalendar.get(Calendar.MONTH)}.${mathCalendar.get(Calendar.DAY_OF_MONTH)}."
                    }
                    //100일
                    3 -> {
                        mathCalendar.add(Calendar.DAY_OF_MONTH,100)
                        dday1.text="${mathCalendar.get(Calendar.YEAR)}.${mathCalendar.get(Calendar.MONTH)}.${mathCalendar.get(Calendar.DAY_OF_MONTH)}."
                    }
                    //1년
                    4 -> {
                        intMetYear+=1
                        dday1.text="$intMetYear. $intMetMonth. $intMetDay"
                    }
                    //1000일
                    5 -> {
                        mathCalendar.add(Calendar.DAY_OF_MONTH,1000)
                        dday1.text="${mathCalendar.get(Calendar.YEAR)}.${mathCalendar.get(Calendar.MONTH)}.${mathCalendar.get(Calendar.DAY_OF_MONTH)}."
                    }
                    //2000일
                    6 -> {
                        mathCalendar.add(Calendar.DAY_OF_MONTH,2000)
                        dday1.text="${mathCalendar.get(Calendar.YEAR)}.${mathCalendar.get(Calendar.MONTH)}.${mathCalendar.get(Calendar.DAY_OF_MONTH)}."
                    }
                }
            }
        }
        //spinner2 선택 리스너
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

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

                    //처음만난날
                    0 -> {
                        dday1.text="$intMetYear. $intMetMonth. $intMetDay"
                    }
                    //10일
                    1 -> {
                        mathCalendar.add(Calendar.DAY_OF_MONTH,10)
                        dday2.text="${mathCalendar.get(Calendar.YEAR)}.${mathCalendar.get(Calendar.MONTH)}.${mathCalendar.get(Calendar.DAY_OF_MONTH)}."
                    }
                    //50일
                    2 -> {
                        mathCalendar.add(Calendar.DAY_OF_MONTH,50)
                        dday2.text="${mathCalendar.get(Calendar.YEAR)}.${mathCalendar.get(Calendar.MONTH)}.${mathCalendar.get(Calendar.DAY_OF_MONTH)}."
                    }
                    //100일
                    3 -> {
                        mathCalendar.add(Calendar.DAY_OF_MONTH,100)
                        dday2.text="${mathCalendar.get(Calendar.YEAR)}.${mathCalendar.get(Calendar.MONTH)}.${mathCalendar.get(Calendar.DAY_OF_MONTH)}."
                    }
                    //1년
                    4 -> {
                        intMetYear+=1
                        dday2.text="$intMetYear. $intMetMonth. $intMetDay"
                    }
                    //1000일
                    5 -> {
                        mathCalendar.add(Calendar.DAY_OF_MONTH,1000)
                        dday2.text="${mathCalendar.get(Calendar.YEAR)}.${mathCalendar.get(Calendar.MONTH)}.${mathCalendar.get(Calendar.DAY_OF_MONTH)}."
                    }
                    //2000일
                    6 -> {
                        mathCalendar.add(Calendar.DAY_OF_MONTH,2000)
                        dday2.text="${mathCalendar.get(Calendar.YEAR)}.${mathCalendar.get(Calendar.MONTH)}.${mathCalendar.get(Calendar.DAY_OF_MONTH)}."
                    }
                }
            }
        }
        //spinner3 선택 리스너
        spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

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

                    //처음만난날
                    0 -> {
                        dday3.text="$intMetYear. $intMetMonth. $intMetDay"
                    }
                    //10일
                    1 -> {
                        mathCalendar.add(Calendar.DAY_OF_MONTH,10)
                        dday3.text="${mathCalendar.get(Calendar.YEAR)}.${mathCalendar.get(Calendar.MONTH)}.${mathCalendar.get(Calendar.DAY_OF_MONTH)}."
                    }
                    //50일
                    2 -> {
                        mathCalendar.add(Calendar.DAY_OF_MONTH,50)
                        dday3.text="${mathCalendar.get(Calendar.YEAR)}.${mathCalendar.get(Calendar.MONTH)}.${mathCalendar.get(Calendar.DAY_OF_MONTH)}."
                    }
                    //100일
                    3 -> {
                        mathCalendar.add(Calendar.DAY_OF_MONTH,100)
                        dday3.text="${mathCalendar.get(Calendar.YEAR)}.${mathCalendar.get(Calendar.MONTH)}.${mathCalendar.get(Calendar.DAY_OF_MONTH)}."
                    }
                    //1년
                    4 -> {
                        intMetYear+=1
                        dday3.text="$intMetYear. $intMetMonth. $intMetDay"
                    }
                    //1000일
                    5 -> {
                        mathCalendar.add(Calendar.DAY_OF_MONTH,1000)
                        dday3.text="${mathCalendar.get(Calendar.YEAR)}.${mathCalendar.get(Calendar.MONTH)}.${mathCalendar.get(Calendar.DAY_OF_MONTH)}."
                    }
                    //2000일
                    6 -> {
                        mathCalendar.add(Calendar.DAY_OF_MONTH,2000)
                        dday3.text="${mathCalendar.get(Calendar.YEAR)}.${mathCalendar.get(Calendar.MONTH)}.${mathCalendar.get(Calendar.DAY_OF_MONTH)}."
                    }
                }
            }
        }


    }
    private fun setResultNext(){
        activityResultLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode==RESULT_OK){
                var date=intent.getStringExtra("date")
                var things=intent.getStringExtra("things")

                var celebrate=findViewById<TextView>(R.id.celebrate)
                var celebratethings=findViewById<TextView>(R.id.celabratethings)
                celebrate.text=date
                celebratethings.text=things
            }
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