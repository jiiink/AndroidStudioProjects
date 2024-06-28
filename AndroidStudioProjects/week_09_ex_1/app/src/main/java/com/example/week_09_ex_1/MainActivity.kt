package com.example.week_09_ex_1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    lateinit var output1: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        output1 = findViewById(R.id.output1)
        val EditName = findViewById<EditText>(R.id.name)
        val EditEmail = findViewById<EditText>(R.id.email)
        val EditAge = findViewById<EditText>(R.id.age)

        val readBtn: Button = findViewById(R.id.readBtn)
        val writeBtn: Button = findViewById(R.id.writeBtn)


        readBtn.setOnClickListener{
            readFirebase()
        }

        writeBtn.setOnClickListener{
            var Name: String
            var Email: String
            var Age: Int = 0
            if (EditName.length() == 0) Name = "null" else Name = EditName.text.toString()
            if (EditEmail.length() == 0) Email = "null" else Email = EditEmail.text.toString()
            if (EditAge.length() == 0) Age = 0 else Age = EditAge.text.toString().toInt()

            writeFirebase(Name, Email, Age)
        }

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }

    fun readFirebase() {
        db.collection("users")
            .get()
            .addOnSuccessListener {
                result -> for (document in result)
                    output1.append("${document.data} \n")
            }
            .addOnFailureListener {
                output1.append("Failure \n")
            }
    }

    fun writeFirebase(Name: String, Email: String, Age: Int) {
        val user = mapOf(
            "name" to Name,
            "email" to Email,
            "age" to Age
        )
        val colRef: CollectionReference = db.collection("users")
        val docRef = colRef.add(user)
        docRef.addOnSuccessListener {
            documentReference -> output1.append("Success : " + "${documentReference.id} \n")
        }
        docRef.addOnFailureListener{
            output1.append("Failure \n")
        }
    }
}