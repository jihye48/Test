package com.example.test

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PopupActivity1 : AppCompatActivity() {

    lateinit var noButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup1)

        noButton=findViewById(R.id.noButton)
        noButton.setOnClickListener{
            finish()
        }

    }
}