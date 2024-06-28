package com.example.week_07_ex_3

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.week_07_ex_3.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import java.text.DecimalFormat
import java.time.LocalTime

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isRunning = false
    private var isPaused = false
    private lateinit var job: Job
    private lateinit var channel: Channel<String>
    private var hours = 0
    private var minutes = 0
    private var seconds = 0

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        channel = Channel()

        binding.start.setOnClickListener {
            if (!isRunning && !isPaused) {
                isRunning = true
                job = startTimer()
            } else {
                showToast("Stopwatch is not stopped now!!")
            }
        }

        binding.stop.setOnClickListener {
            if (isRunning) {
                isRunning = false
                isPaused = false
                job.cancel()
                resetTimer()
            }
        }

        binding.pause.setOnClickListener {
            if (isRunning && !isPaused) {
                isPaused = true
                job.cancel()
            }
        }

        binding.resume.setOnClickListener {
            if (isRunning && isPaused) {
                isPaused = false
                job = startTimer()
            }
        }

        val subScope = CoroutineScope(Dispatchers.Default + Job())
        subScope.launch {
            channel.consumeEach {
                withContext(Dispatchers.Main) {
                    binding.time.text = it
                }
            }
        }
    }

    private fun startTimer(): Job {
        return CoroutineScope(Dispatchers.Default).launch {
            while (isActive) {
                delay(1000)
                seconds++
                if (seconds == 60) {
                    seconds = 0
                    minutes++
                    if (minutes == 60) {
                        minutes = 0
                        hours++
                    }
                }
                val timeString = formatTime(hours, minutes, seconds)
                channel.send(timeString)
            }
        }
    }

    private fun resetTimer() {
        hours = 0
        minutes = 0
        seconds = 0
        binding.time.text = "00:00:00"
    }

    private fun formatTime(hours: Int, minutes: Int, seconds: Int): String {
        val df = DecimalFormat("00")
        return "${df.format(hours)}:${df.format(minutes)}:${df.format(seconds)}"
    }
}
