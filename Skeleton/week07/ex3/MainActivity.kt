package com.example.stopwatch

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.stopwatch.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import java.text.DecimalFormat
import java.time.LocalTime



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.activityMain)
        var isrunning = false
        var ispause = false
        val channel = Channel<String>()
        var h = 0; var m = 0; var s = 0
        binding.start.setOnClickListener {
            "///////todo////////"
        }
        binding.stop.setOnClickListener {
            "///////todo////////"
        }
        binding.pause.setOnClickListener {
            "///////todo////////"
        }
        binding.resume.setOnClickListener {
            "///////todo////////"
        }
        val subScope = CoroutineScope(Dispatchers.Default + Job())
        subScope.launch {
            "///////todo////////"
        }
        var mainScope = GlobalScope.launch(Dispatchers.Main) {
            channel.consumeEach {
                binding.time.text = "$it"
            }
        }
    }
}