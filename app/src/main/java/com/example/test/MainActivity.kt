package com.example.test

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.DatePicker
import com.example.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    lateinit var button: Button


    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.imageBtn.setOnClickListener{
            val intent = Intent(this, SubActivity::class.java)
            val dataPicker = findViewById<DatePicker>(R.id.datePicker)
            val n = 2
            intent.putExtra("year",dataPicker.year.toString())
            intent.putExtra("month",(dataPicker.month+1).toString().padStart(n,'0'))
            intent.putExtra("day",dataPicker.dayOfMonth.toString().padStart(n,'0'))
            startActivity(intent)
        }
    }



//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.actionmenu, menu)
        return true
    }



}

