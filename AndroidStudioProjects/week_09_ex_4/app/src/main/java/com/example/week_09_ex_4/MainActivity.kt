package com.example.week_09_ex_4

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.week_09_ex_4.databinding.ActivityMainBinding
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.concurrent.timer


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val db = Firebase.firestore
    private var oSysMainLoop = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adocRef = db.collection("data").document("BkB52bMUZssl5CK6wEAY")

        adocRef.addSnapshotListener(EventListener<DocumentSnapshot> { snapshot, e ->
            if (snapshot != null && snapshot.exists()) {
                binding.textView.text = snapshot.data!!["xloc"].toString()
            }
        })

        if (oSysMainLoop == 0) {
            oSysMainLoop = 1
            timer(period = 1500, initialDelay = 1000) {
                if (oSysMainLoop != 1) {
                    cancel()
                }
                val axloc = hashMapOf("xloc" to (0..100).random())
                db.collection("data").document("BkB52bMUZssl5CK6wEAY").set(axloc)
                    .addOnSuccessListener {
                        showToast("Data updated successfully")
                    }
                    .addOnFailureListener {
                        showToast("Failed to update data")
                    }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
