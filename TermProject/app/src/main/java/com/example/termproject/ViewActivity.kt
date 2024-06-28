package com.example.termproject

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ViewActivity : AppCompatActivity() {
    private var isImageVisible = false
    private var image: ByteArray? = null
    private var diaryId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        val date = intent.getLongExtra("date", 0L)
        image = intent.getByteArrayExtra("image")
        diaryId = intent.getIntExtra("diaryId", -1)

        val titleView: TextView = findViewById(R.id.titleTextView)
        val contentView: TextView = findViewById(R.id.contentTextView)
        val dateView: TextView = findViewById(R.id.dateTextView)
        val toggleImageButton: Button = findViewById(R.id.toggleImageButton)
        val finishBtn: Button = findViewById(R.id.finishBtn)
        val deleteBtn: Button = findViewById(R.id.deleteBtn)  // Assuming you have this button in your layout

        titleView.text = title
        contentView.text = content
        dateView.text = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(date))

        toggleImageButton.setOnClickListener {
            toggleImageFragment()
        }

        finishBtn.setOnClickListener {
            finish()
        }

        deleteBtn.setOnClickListener {
            deleteDiary()
        }
    }

    private fun toggleImageFragment() {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        if (isImageVisible) {
            val fragment = fragmentManager.findFragmentByTag("IMAGE_FRAGMENT")
            fragment?.let {
                fragmentTransaction.remove(it)
            }
            isImageVisible = false
        } else {
            image?.let {
                val fragment = ImageFragment.newInstance(it)
                fragmentTransaction.replace(R.id.imageFragmentContainer, fragment, "IMAGE_FRAGMENT")
                isImageVisible = true
            }
        }

        fragmentTransaction.commit()
    }

    private fun deleteDiary() {
        if (diaryId != -1) {
            val dbHelper = DiaryDBHelper(this)
            val success = dbHelper.deleteDiary(diaryId)
            if (success) {
                Toast.makeText(this, "Diary deleted successfully", Toast.LENGTH_SHORT).show()
                setResult(Activity.RESULT_OK)  // Set result to OK
                finish()  // Close the activity after deletion
            } else {
                Toast.makeText(this, "Failed to delete diary", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Invalid diary ID", Toast.LENGTH_SHORT).show()
        }
    }
}
