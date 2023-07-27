package com.example.test

import android.annotation.SuppressLint
import java.io.FileInputStream
import java.io.FileOutputStream

import android.view.View
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity


class calActivity() : AppCompatActivity(), Parcelable {
    private var userID: String = "userID"
    private lateinit var fname: String
    private lateinit var str: String
    private lateinit var calendarView: CalendarView
    private lateinit var deleteBtn:Button
    private lateinit var saveBtn:Button
    private lateinit var diaryContent:TextView
    private lateinit var editText: EditText

    constructor(parcel: Parcel) : this() {
        userID = parcel.readString().toString()
        fname = parcel.readString().toString()
        str = parcel.readString().toString()
    }


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cal)

        setSupportActionBar(findViewById(R.id.main_tool_bar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // UI값 생성
        calendarView=findViewById(R.id.calendarView)
        saveBtn=findViewById(R.id.saveBtn)
        deleteBtn=findViewById(R.id.deleteBtn)
        diaryContent=findViewById(R.id.diaryContent)
        editText=findViewById(R.id.editText)

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            saveBtn.visibility = View.VISIBLE
            editText.visibility = View.VISIBLE
            diaryContent.visibility = View.INVISIBLE
            deleteBtn.visibility = View.INVISIBLE
            editText.setText("")
            checkDay(year, month, dayOfMonth, userID)
        }

        saveBtn.setOnClickListener {
            saveDiary(fname)
            editText.visibility = View.INVISIBLE
            saveBtn.visibility = View.INVISIBLE
            deleteBtn.visibility = View.VISIBLE
            str = editText.text.toString()
            diaryContent.text = str
            diaryContent.visibility = View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.returnsub, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                //toolbar의 back키 눌렀을 때 동작
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // 달력 내용 조회, 수정
    private fun checkDay(cYear: Int, cMonth: Int, cDay: Int, userID: String) {
        //저장할 파일 이름 설정
        fname = "" + userID + cYear + "-" + (cMonth + 1) + "" + "-" + cDay + ".txt"

        val fileInputStream: FileInputStream
        try {
            fileInputStream = openFileInput(fname)
            val fileData = ByteArray(fileInputStream.available())
            fileInputStream.read(fileData)
            fileInputStream.close()
            str = String(fileData)
            editText.visibility = View.INVISIBLE
            diaryContent.visibility = View.VISIBLE
            diaryContent.text = str
            saveBtn.visibility = View.INVISIBLE
            deleteBtn.visibility = View.VISIBLE

            deleteBtn.setOnClickListener {
                diaryContent.visibility = View.INVISIBLE
                deleteBtn.visibility = View.INVISIBLE
                editText.setText("")
                editText.visibility = View.VISIBLE
                saveBtn.visibility = View.VISIBLE
                removeDiary(fname)
            }
            if (diaryContent.text == null) {
                diaryContent.visibility = View.INVISIBLE
                deleteBtn.visibility = View.INVISIBLE
                saveBtn.visibility = View.VISIBLE
                editText.visibility = View.VISIBLE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    // 달력 내용 제거
    @SuppressLint("WrongConstant")
    fun removeDiary(readDay: String?) {
        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = openFileOutput(readDay, MODE_NO_LOCALIZED_COLLATORS)
            val content = ""
            fileOutputStream.write(content.toByteArray())
            fileOutputStream.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }


    // 달력 내용 추가
    @SuppressLint("WrongConstant")
    fun saveDiary(readDay: String?) {
        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = openFileOutput(readDay, MODE_NO_LOCALIZED_COLLATORS)
            val content = editText.text.toString()
            fileOutputStream.write(content.toByteArray())
            fileOutputStream.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userID)
        parcel.writeString(fname)
        parcel.writeString(str)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<calActivity> {
        override fun createFromParcel(parcel: Parcel): calActivity {
            return calActivity(parcel)
        }

        override fun newArray(size: Int): Array<calActivity?> {
            return arrayOfNulls(size)
        }
    }
}