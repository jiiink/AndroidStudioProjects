package com.example.week_04_ex_03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.view.View

class MainActivity : AppCompatActivity() {
    lateinit var btnLeft: Button
    lateinit var btnRight: Button
    lateinit var img: ImageView
    var idx: Int = 0
    val maxIdx: Int = 25  // Maximum index of images
    val minIdx: Int = 0    // Minimum index of images

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "예제3"

        btnLeft = findViewById(R.id.leftBtn)
        btnRight = findViewById(R.id.rightBtn)

        img = findViewById(R.id.imgView)

        btnLeft.setOnClickListener {
            if (idx > minIdx) {
                idx--
                changeImg(idx)
            }
        }

        btnRight.setOnClickListener {
            if (idx < maxIdx) {
                idx++
                changeImg(idx)
            }
        }
    }

    private fun changeImg(idx: Int) {
        when (idx) {
            0 -> img.setImageResource(R.drawable.a)
            1 -> img.setImageResource(R.drawable.b)
            2 -> img.setImageResource(R.drawable.c)
            3 -> img.setImageResource(R.drawable.d)
            4 -> img.setImageResource(R.drawable.e)
            5 -> img.setImageResource(R.drawable.f)
            6 -> img.setImageResource(R.drawable.g)
            7 -> img.setImageResource(R.drawable.h)
            8 -> img.setImageResource(R.drawable.i)
            9 -> img.setImageResource(R.drawable.j)
            10 -> img.setImageResource(R.drawable.k)
            11 -> img.setImageResource(R.drawable.l)
            12 -> img.setImageResource(R.drawable.m)
            13 -> img.setImageResource(R.drawable.n)
            14 -> img.setImageResource(R.drawable.o)
            15 -> img.setImageResource(R.drawable.p)
            16 -> img.setImageResource(R.drawable.q)
            17 -> img.setImageResource(R.drawable.r)
            18 -> img.setImageResource(R.drawable.s)
            19 -> img.setImageResource(R.drawable.t)
            20 -> img.setImageResource(R.drawable.u)
            21 -> img.setImageResource(R.drawable.v)
            22 -> img.setImageResource(R.drawable.w)
            23 -> img.setImageResource(R.drawable.x)
            24 -> img.setImageResource(R.drawable.y)
            25 -> img.setImageResource(R.drawable.z)
        }
    }
}
