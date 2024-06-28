package com.example.week_06_ex_3

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val name_age:TextView=findViewById(R.id.name_age_result)
        val phone:TextView=findViewById(R.id.phone_result)
        val address:TextView=findViewById(R.id.address_result)
        val etc:TextView=findViewById(R.id.etc_result)
        val image:ImageView=findViewById(R.id.image_result)

        val name = intent.getStringExtra("name")
        val age = intent.getStringExtra("age")
        name_age.text = "이름: $name 나이: $age"
        phone.text = intent.getStringExtra("phone")
        address.text = intent.getStringExtra("address")
        etc.text = intent.getStringExtra("etc")

        val byteArray: ByteArray? = intent.getByteArrayExtra("image")
        val bitmap = byteArray?.let { BitmapFactory.decodeByteArray(byteArray, 0, it.size) }
        image.setImageBitmap(bitmap)
        Toast.makeText(applicationContext,"정상적으로 저장되었습니다",Toast.LENGTH_SHORT).show()


    }
}