package com.example.week_06_ex_2

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SubActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val data = intent.getStringExtra("data")
        val btn: Button = findViewById(R.id.btn_sub)
        val ev = findViewById<EditText>(R.id.ev_sub)
        val res: TextView = findViewById(R.id.res_sub)
        res.text = "$data\n-send from main"

        btn.setOnClickListener {
            if (ev.text.isEmpty()) {
                setResult(Activity.RESULT_CANCELED)
            } else {
                val newData = ev.text.toString()
                val intent = Intent()
                intent.putExtra("data", newData)
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
    }
}

