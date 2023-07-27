package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PopupActivity2 : AppCompatActivity() {

    lateinit var dateView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup2)

        dateView=findViewById(R.id.dateView)

        if(intent.hasExtra("year")){
            dateView.text=intent.getStringExtra("year")
        }
    }
}