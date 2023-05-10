package com.abdullah.univeraproject.di.service

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.LifecycleService
import java.util.*


class MessageService : LifecycleService() {


    init {
        startStopwatch()
    }

    private lateinit var stopwatchTimer: Timer


    private fun startStopwatch() {


        stopwatchTimer = Timer()
        stopwatchTimer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val handler = Handler(Looper.getMainLooper())
                handler.post {
                    Toast.makeText(
                        applicationContext,
                        "every 5 minute",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }, 0, 5 * 60 * 1000)
    }
}