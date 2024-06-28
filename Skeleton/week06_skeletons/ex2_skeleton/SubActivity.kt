package com.example.intentactivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.###TODO###)

        val data = intent.###TODO###("data")
        val btn: Button = findViewById(###TODO###)
        val ev = findViewById<EditText>(###TODO###)
        val res: TextView = findViewById(###TODO###)
        res.text = "###TODO###\n-send from main"

        btn.setOnClickListener {
            #####
		TODO
			#####
            val data: String = ev.text.toString()
            ###TODO###
            intent.###TODO###
            setResult(Activity.RESULT_OK, intent)
            ###TODO###
            finish()
        }
    }
}

