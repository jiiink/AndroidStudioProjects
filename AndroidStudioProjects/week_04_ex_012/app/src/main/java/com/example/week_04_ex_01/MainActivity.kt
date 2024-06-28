package com.example.week_04_ex_01


import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    lateinit var btnChanger : Button
    lateinit var linLayer : LinearLayout

    var sequence : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "예제 1"

        // Initialize views
        btnChanger = findViewById(R.id.ChangeBtn)
        linLayer = findViewById(R.id.LinLay)

        btnChanger.setOnClickListener {
            // Change background color of linLayer based on sequence
            when (sequence) {
                0 -> {
                    linLayer.setBackgroundColor(Color.parseColor("#00FF00"))
                    sequence = 1
                }
                1 -> {
                    linLayer.setBackgroundColor(Color.parseColor("#0000FF"))
                    sequence = 2
                }
                2 -> {
                    linLayer.setBackgroundColor(Color.parseColor("#FF0000"))
                    sequence = 0
                }
            }
            Log.d("MainActivity", "Background color changed")
        }
    }
}
