package com.example.bound_service_project

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Binder
import android.os.Handler
import android.os.Looper
import android.os.SystemClock

class StopwatchService : Service() {

    private val binder = StopwatchBinder()
    private var running = false
    private var elapsedTime: Long = 0
    private val handler = Handler(Looper.getMainLooper())
    private var startTime: Long = 0
    private var pauseTime: Long = 0
    private var paused = false


    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    inner class StopwatchBinder : Binder(){
        fun getService(): StopwatchService = this@StopwatchService
    }


    fun start(){
        running = true
        startTime = SystemClock.elapsedRealtime()
        updateRunnable.run()
    }

    fun pause(){
        running = false
        paused = true
        pauseTime = elapsedTime
    }

    fun stop(){
        running = false
        paused = false
        elapsedTime = 0
    }

    private val updateRunnable = object : Runnable {
        override fun run() {
            if (running && paused) {
                val timeNow = SystemClock.elapsedRealtime()
                elapsedTime = pauseTime + timeNow - startTime
                handler.postDelayed(this, 100)
            }
            else if(running && !paused){
                val timeNow = SystemClock.elapsedRealtime()
                elapsedTime =timeNow - startTime
                handler.postDelayed(this, 100)
            }
        }
    }

    fun getElapsedTime() : Long{
        return elapsedTime
    }

}