package com.example.bound_service_project

import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.bound_service_project.databinding.ActivityMainBinding
import android.content.ServiceConnection
import android.os.IBinder

class MainActivity : AppCompatActivity() {

    private var stopwatchService: StopwatchService? = null
    private var isBound = false
    private lateinit var binding: ActivityMainBinding

    private val connection = object: ServiceConnection{
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as StopwatchService.StopwatchBinder
            stopwatchService = binder.getService()
            isBound = true
        }
        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener{
            stopwatchService?.start()
            updateElapsedTime()
        }

        binding.button2.setOnClickListener{
            stopwatchService?.pause()
        }

        binding.button3.setOnClickListener{
            stopwatchService?.stop()
        }
    }

    private fun updateElapsedTime() {
        binding.tvTimer.postDelayed({
            if (isBound && stopwatchService != null) {
                // Retrieve the elapsed time from the service
                val elapsedTime = stopwatchService?.getElapsedTime() ?: 0L
                // Calculate hours, minutes, and seconds from elapsed time
                val seconds = (elapsedTime / 1000) % 60
                val minutes = (elapsedTime / (1000 * 60)) % 60
                val hours = (elapsedTime / (1000 * 60 * 60)) % 24
                // Update the UI with the formatted time
                binding.tvTimer.text = String.format("%02d:%02d:%02d", hours, minutes, seconds)
            }
            // Re-run updateElapsedTime every second
            updateElapsedTime()
        }, 1000)
    }

    override fun onStart() {
        super.onStart()
        //bind to the service
        Intent(this, StopwatchService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        if (isBound) {
            unbindService(connection)
            isBound = false
        }
    }
}