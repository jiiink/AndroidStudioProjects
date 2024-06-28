
package com.example.week_10_ex_2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
//import kotlinx.android.synthetic.main.activity_reserved.input1
//import kotlinx.android.synthetic.main.activity_reserved.input2
//import kotlinx.android.synthetic.main.activity_reserved.input3
//import kotlinx.android.synthetic.main.activity_reserved.input4
//import kotlinx.android.synthetic.main.activity_main.posterImageView
//import kotlinx.android.synthetic.main.activity_reserved.*
import java.io.Serializable

data class ReservedMovie(
    val _id: Int?,
    val name: String?,
    val poster_image: String?,
    val director: String?,
    val synopsis: String?,
    val reserved_time: String?
): Serializable

class ReservedActivity : AppCompatActivity() {
    lateinit var input1: TextView
    lateinit var input2: TextView
    lateinit var input3: TextView
    lateinit var input4: TextView
    lateinit var closeButton: Button
    lateinit var posterImageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserved)

        closeButton = findViewById(R.id.closeButton)
        posterImageView = findViewById(R.id.posterImageView)
        input1 = findViewById(R.id.input1)
        input2 = findViewById(R.id.input2)
        input3 = findViewById(R.id.input3)
        input4 = findViewById(R.id.input4)

        processIntent(intent)
        // TODO : 버튼을 눌렀을 때 ReservedActivity 종료
        closeButton.setOnClickListener {
            finish()
        }
    }

    fun processIntent(intent: Intent?){
        val movies = intent?.getSerializableExtra("movies") as ArrayList<ReservedMovie>?
        val movie = movies?.get(movies.size - 1)
        if(movie!=null){
            posterImageView.setImageURI(Uri.parse(movie.poster_image))
            // TODO : 예약 정보 출력
            displayReservationInfo(movie)
        }else{
            input1.setText("null")
            input2.setText("null")
            input3.setText("null")
            input4.setText("null")
        }
    }

    private fun displayReservationInfo(movie: ReservedMovie) {
        input1.text = movie.name ?: ""
        input2.text = movie.reserved_time ?: ""
        input3.text = movie.director ?: ""
        input4.text = movie.synopsis ?: ""
    }
}
