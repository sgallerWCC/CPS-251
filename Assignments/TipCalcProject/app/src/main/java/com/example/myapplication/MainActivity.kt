package com.example.myapplication

import android.icu.text.DecimalFormat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener{
            if(binding.input.text!!.isEmpty()){
                binding.output.text = getString(R.string.tipError)
            }
            else{
                val billAmount = binding.input.text.toString().toDouble()
                binding.output.text = calcTips(billAmount)
            }
        }
    }

    private fun calcTips(bill : Double) : String{
        val formatter = DecimalFormat("#,###.00")
        return "Tips are as follows: \n \n 10% = $${formatter.format(bill*1.1)}" +
                "\n 15% = $${formatter.format(bill*1.15)}" +
                "\n 20% = $${formatter.format(bill*1.2)}"
    }
}