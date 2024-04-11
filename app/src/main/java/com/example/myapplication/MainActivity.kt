package com.example.myapplication

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var startStopwatchButton: Button
    private var stopwatchRunning = false
    private var seconds = 0
    private var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startStopwatchButton = findViewById(R.id.startStopwatchButton)
        startStopwatchButton.setOnClickListener {
            if (!stopwatchRunning) {
                startStopwatch()
                startStopwatchButton.text = "Stop Stopwatch"
            } else {
                stopStopwatch()
                startStopwatchButton.text = "Start Stopwatch"
            }
            stopwatchRunning = !stopwatchRunning
        }
    }

    private fun startStopwatch() {
        handler = Handler(Looper.getMainLooper())
        handler?.post(object : Runnable {
            override fun run() {
                seconds++
                showToast("Секундомер: $seconds секунд")
                handler?.postDelayed(this, 1000)
            }
        })
    }

    private fun stopStopwatch() {
        handler?.removeCallbacksAndMessages(null)
        seconds = 0
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

