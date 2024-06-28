package com.example.week_06_ex_3

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.ByteArrayOutputStream

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val nameAge: TextView = findViewById(R.id.name_age_sub)
        val phone: TextView = findViewById(R.id.phone_sub)
        val address: TextView = findViewById(R.id.address_sub)
        val etc: TextView = findViewById(R.id.etc_sub)
        val image: ImageView = findViewById(R.id.image_sub)
        val btnChange: Button = findViewById(R.id.change_sub)
        val btnReturn: Button = findViewById(R.id.return_sub)

        val name = intent.getStringExtra("name")
        val age = intent.getStringExtra("age")
        nameAge.text = "이름: $name 나이: $age"
        phone.text = "연락처: " + intent.getStringExtra("phone")
        address.text = "주소: " + intent.getStringExtra("address")
        etc.text = "메모: " + intent.getStringExtra("etc")

        val byteArray: ByteArray? = intent.getByteArrayExtra("image")
        val bitmap = byteArray?.let { BitmapFactory.decodeByteArray(byteArray, 0, it.size) }
        image.setImageBitmap(bitmap)

        btnReturn.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("age", age)
            intent.putExtra("phone", phone.text.toString())
            intent.putExtra("address", address.text.toString())
            intent.putExtra("etc", etc.text.toString())

            val stream = ByteArrayOutputStream()
            val bitmap = (image.drawable as BitmapDrawable).bitmap
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val byteArray = stream.toByteArray()
            intent.putExtra("image", byteArray)

            startActivity(intent)
            finish()
        }



        btnChange.setOnClickListener {
            finish()
        }
    }


}
