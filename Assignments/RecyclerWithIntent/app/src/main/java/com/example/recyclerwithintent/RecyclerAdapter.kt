package com.example.recyclerwithintent

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.recyclerwithintent.databinding.CardLayoutBinding

class RecyclerAdapter(private val data: Data, private val viewModel: MainViewModel
    ,private val context: Context) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: CardLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener { v ->
                val position = adapterPosition

                if (position != RecyclerView.NO_POSITION) { // Check if position is valid
                    val intent = Intent(context, SecondActivity::class.java).apply{
                        putExtra("title",viewModel.titleArr[position])
                        putExtra("detail",viewModel.detailArr[position])
                        putExtra("image",viewModel.imagesArr[position])
                    }
                    context.startActivity(intent)
                }
            }
        }

        fun bind(title: String, detail: String, imageResId: Int) {
            binding.itemImage.setImageResource(imageResId)
            binding.itemTitle.text = title
            binding.itemDetail.text = detail
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.bind is referencing the bind function written above in the inner class
        holder.bind(data.titles[viewModel.titleArr[position]], data.details[viewModel.detailArr[position]]
            , data.images[viewModel.imagesArr[position]])
    }

    override fun getItemCount(): Int = data.titles.size
}