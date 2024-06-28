package com.example.termproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DiaryDBHelper
    private lateinit var writeButton: Button
    private lateinit var searchButton: Button
    private lateinit var searchEditText: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DiaryAdapter

    companion object {
        private const val WRITE_REQUEST_CODE = 1
        const val VIEW_REQUEST_CODE = 2
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        writeButton = findViewById(R.id.writeButton)
        searchButton = findViewById(R.id.searchButton)
        searchEditText = findViewById(R.id.searchEditText)
        recyclerView = findViewById(R.id.recyclerView)

        dbHelper = DiaryDBHelper(this)

        writeButton.setOnClickListener {
            val intent = Intent(this, WriteActivity::class.java)
            startActivityForResult(intent, WRITE_REQUEST_CODE)
        }

        searchButton.setOnClickListener {
            val searchQuery = searchEditText.text.toString().trim()
            val diaries = dbHelper.getDiariesByTitleOrDate(searchQuery)
            if (diaries.isNotEmpty()) {
                adapter.submitList(diaries)
            } else {
                adapter.submitList(emptyList())
            }
        }

        adapter = DiaryAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                WRITE_REQUEST_CODE -> {
                    val title = data?.getStringExtra("title")
                    val content = data?.getStringExtra("content")
                    val date = data?.getLongExtra("date", -1L) ?: -1L
                    val imagePath = data?.getStringExtra("imagePath")

                    if (title != null && content != null && date != -1L) {
                        val image = imagePath?.let { File(it).readBytes() }
                        dbHelper.addDiary(title, content, date, image)
                    }
                }
                VIEW_REQUEST_CODE -> {
                    refreshRecyclerViewData()
                }
            }
        }
    }

    private fun refreshRecyclerViewData() {
        val diaries = dbHelper.getAllDiaries()
        adapter.submitList(diaries)
    }
}
