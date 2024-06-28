package com.example.termproject

// WriteActivity.kt
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Locale

class WriteActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var contentEditText: EditText
    private lateinit var dateEditText: EditText
    private lateinit var diaryImageView: ImageView
    private lateinit var submitButton: Button
    private lateinit var backButton: Button
    private var selectedImageUri: Uri? = null
    private val PICK_IMAGE_REQUEST = 1
    private val TAG = "WriteActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        titleEditText = findViewById(R.id.titleEditText)
        contentEditText = findViewById(R.id.contentEditText)
        dateEditText = findViewById(R.id.dateEditText)
        diaryImageView = findViewById(R.id.diaryImageView)
        submitButton = findViewById(R.id.submitButton)
        backButton = findViewById(R.id.backButton)

        diaryImageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

        submitButton.setOnClickListener {
            val title = titleEditText.text.toString().trim()
            val content = contentEditText.text.toString().trim()
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateEditText.text.toString())?.time ?: System.currentTimeMillis()

            if (title.isNotEmpty() && content.isNotEmpty()) {
                val imagePath = selectedImageUri?.let { saveImageToFile(it).absolutePath }
                val resultIntent = Intent().apply {
                    putExtra("title", title)
                    putExtra("content", content)
                    putExtra("date", date)
                    putExtra("imagePath", imagePath)
                }
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            } else {
                Log.e(TAG, "Title or content is empty")
            }
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                selectedImageUri = uri
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                diaryImageView.setImageBitmap(bitmap)
            }
        }
    }

    private fun saveImageToFile(imageUri: Uri): File {
        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
        val file = File(filesDir, "diary_image_${System.currentTimeMillis()}.jpg")
        val outputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.flush()
        outputStream.close()
        return file
    }
}
