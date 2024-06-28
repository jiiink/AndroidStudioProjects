package com.example.week_04_ex_04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var edit1 : EditText
    lateinit var edit2 : EditText
    lateinit var btnAdd : Button
    lateinit var btnSub : Button
    lateinit var btnMul : Button
    lateinit var btnDiv : Button
    lateinit var textResult : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edit1 = findViewById(R.id.Edit1)
        edit2 = findViewById(R.id.Edit2)

        btnAdd = findViewById(R.id.BtnAdd)
        btnSub = findViewById(R.id.BtnSub)
        btnMul = findViewById(R.id.BtnMul)
        btnDiv = findViewById(R.id.BtnDiv)

        textResult = findViewById(R.id.TextResult)

        btnAdd.setOnClickListener {
            calculate('+')
        }

        btnSub.setOnClickListener {
            calculate('-')
        }

        btnMul.setOnClickListener {
            calculate('*')
        }

        btnDiv.setOnClickListener {
            calculate('/')
        }
    }

    private fun calculate(operator: Char) {
        val num1Str = edit1.text.toString()
        val num2Str = edit2.text.toString()

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            edit1.setText("Please enter valid numbers")
            return
        }

        val num1 = num1Str.toInt()
        val num2 = num2Str.toInt()
        var result = 0

        when (operator) {
            '+' -> result = num1 + num2
            '-' -> result = num1 - num2
            '*' -> result = num1 * num2
            '/' -> {
                if (num2 != 0) {
                    result = num1 / num2
                } else {
                    edit1.setText("Cannot divide by zero")
                    return
                }
            }
        }

        edit1.setText(result.toString())
        edit2.setText("")
    }

}
