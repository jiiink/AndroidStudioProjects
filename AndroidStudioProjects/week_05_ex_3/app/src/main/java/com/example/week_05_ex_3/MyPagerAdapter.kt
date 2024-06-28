package com.example.week_05_ex_3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week_05_ex_3.databinding.ItemPagerBinding

class MyPagerViewHolder(val binding: ItemPagerBinding) : RecyclerView.ViewHolder(binding.root)

class MyPagerAdapter(val datas: MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyPagerViewHolder(ItemPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyPagerViewHolder).binding

        binding.itemPagerTextView.text = datas[position]
        when (position % 3) {
            // TODO : position에 따라 배경색 변경
            0 -> binding.root.setBackgroundColor(binding.root.context.getColor(android.R.color.holo_red_light))
            1 -> binding.root.setBackgroundColor(binding.root.context.getColor(android.R.color.holo_blue_light))
            2 -> binding.root.setBackgroundColor(binding.root.context.getColor(android.R.color.holo_green_light))
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}




