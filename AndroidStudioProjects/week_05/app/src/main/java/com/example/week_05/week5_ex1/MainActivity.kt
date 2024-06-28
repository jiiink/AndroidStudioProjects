package com.example.week_05.week5_ex1

import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.week_05.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO: FragmentManager 및 fragButton 변수 선언
        val fragButton = findViewById<Button>(R.id.fragBut1)
        val fragmentManager: FragmentManager = supportFragmentManager
        var onClicked = false
        fragButton.setOnClickListener{
            if (onClicked) {
                onClicked = false
                // TODO: FragmentManager를 사용하여 Fragment를 제거
            }
            else {
                onClicked=true
                // TODO: FragmentManager를 사용하여 Fragment를 추가
            }
        }
    }
}

