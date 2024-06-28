package com.example.a6_3

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val name_age:TextView=findViewById(###TODO###)
        val phone:TextView=findViewById(###TODO###)
        val address:TextView=findViewById(###TODO###)
        val etc:TextView=findViewById(###TODO###)
        val image:ImageView=findViewById(###TODO###)
        val btn_change:Button=findViewById(###TODO###)
        val btn_return:Button=findViewById(###TODO###)

        name_age.text=intent.###TODO###+"\n"+intent.###TODO###
        phone.text=intent.###TODO###
        address.text=intent.###TODO###
        etc.text=intent.###TODO###
        val bitmap = intent.###TODO###
        image.###TODO###

        btn_return.setOnClickListener {
            ###TODO###
            ###TODO###
        }

        btn_change.setOnClickListener {
            ###TODO###
            ###TODO###
        }



    }
}