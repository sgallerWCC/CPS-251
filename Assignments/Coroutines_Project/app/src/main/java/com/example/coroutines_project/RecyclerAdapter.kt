package com.example.coroutines_project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutines_project.databinding.CardLayoutBinding

class RecyclerAdapter(val viewModel: mainViewModel)  : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: CardLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(nameString: String) {
            binding.tvNameString.text = nameString
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = viewModel.names.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(viewModel.names[position])
    }
}