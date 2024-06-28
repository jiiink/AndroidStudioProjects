package com.example.week_06_ex_3

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.createBitmap
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var etcEditText: EditText
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.name_main)
        ageEditText = findViewById(R.id.age_main)
        phoneEditText = findViewById(R.id.phone_main)
        addressEditText = findViewById(R.id.address_main)
        etcEditText = findViewById(R.id.etc_main)
        imageView = findViewById(R.id.image_main)
        imageView.isClickable = true
        imageView.setOnClickListener {
            openGallery()
        }

        val saveButton: Button = findViewById(R.id.save_main)
        saveButton.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)
            intent.putExtra("name", nameEditText.text.toString())
            intent.putExtra("age", ageEditText.text.toString())
            intent.putExtra("phone", phoneEditText.text.toString())
            intent.putExtra("address", addressEditText.text.toString())
            intent.putExtra("etc", etcEditText.text.toString())

            val stream = ByteArrayOutputStream()
            val bitmap = (imageView.drawable as BitmapDrawable).bitmap
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val byteArray = stream.toByteArray()
            intent.putExtra("image", byteArray)

            startActivityForResult(intent, 100)
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                1 ->{
                    var imageData: Uri? = data?.data
                    try{
                        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageData)
                        imageView.setImageBitmap(bitmap) // MainActivity의 이미지뷰에 설정

                    }catch(e:Exception){
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    private fun clearFields() {
        nameEditText.text.clear()
        ageEditText.text.clear()
        phoneEditText.text.clear()
        addressEditText.text.clear()
        etcEditText.text.clear()
//        imageView.setImageResource(android.R.color.transparent) // Set to default image
    }


//    override fun onResume() {
//        super.onResume()
//        clearFields()
//    }

}
