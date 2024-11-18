package com.example.coroutines_project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutines_project.databinding.ActivityMainBinding
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    private lateinit var viewModel: mainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[mainViewModel::class.java]

        layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        adapter = RecyclerAdapter(viewModel)
        binding.recyclerView.adapter = adapter

        binding.button.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val delayTime = (1..10).random()
                val name = binding.input.text.toString()
                delay(delayTime.toDuration(DurationUnit.SECONDS))
                val str =
                    "The name is " + name + " and the delay was " + delayTime * 1000 + " milliseconds"
                viewModel.addString(str)
                adapter?.notifyDataSetChanged()
            }
        }
            }
    }
