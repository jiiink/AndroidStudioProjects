package com.example.week_04_ex_02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "예제 2번"

        btn1 = findViewById(R.id.Btn1)
        btn2 = findViewById(R.id.Btn2)
        btn3 = findViewById(R.id.Btn3)

        btn1.visibility = View.VISIBLE
        btn2.visibility = View.INVISIBLE
        btn3.visibility = View.INVISIBLE

        btn1.setOnClickListener {
            btn1.visibility = View.INVISIBLE
            btn2.visibility = View.VISIBLE
        }

        btn2.setOnClickListener {
            btn2.visibility = View.INVISIBLE
            btn3.visibility = View.VISIBLE
        }

        btn3.setOnClickListener {
            btn3.visibility = View.INVISIBLE
            btn1.visibility = View.VISIBLE
        }
    }
}
