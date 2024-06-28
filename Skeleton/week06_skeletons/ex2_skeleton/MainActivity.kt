package com.example.

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : ###TODO### {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(###TODO###)
        val btn: Button = findViewById(###TODO###)
        val ev = findViewById<EditText>(###TODO###)
        val res: TextView = findViewById(###TODO###)

        btn.###TODO### {
            if (###TODO###) {
                Toast.###TODO###(this, "값을 입력 해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListzener
            }
            val data: String = ev.text.toString()
            val intent = Intent(this, SubActivity::class.java)
            intent.###TODO###("data", ###TODO###)
            startActivityForResult(###TODO###, 100)
            ev.###TODO###
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val res: TextView = findViewById(R.id.res_main)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                100 -> {
                    res.text = data!!.getStringExtra("data").toString() + "\n-send from sub"
                }
            }
        }
    }
}

