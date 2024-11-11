package com.example.recyclerwithintent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerwithintent.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = Data()

        val titleReceived = intent.getIntExtra("title", 0)
        val detailReceived = intent.getIntExtra("detail", 0)
        val imageReceived = intent.getIntExtra("image", 0)

        binding.titleView.text = data.titles[titleReceived]
        binding.detailView.text = data.details[detailReceived]
        binding.itemImage.setImageResource(data.images[imageReceived])
    }
}

