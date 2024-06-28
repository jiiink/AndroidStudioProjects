package com.example.week_05_ex_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week_05_ex_2.MyAdapter
import com.example.week_05_ex_2.databinding.RecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = RecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val datas = mutableListOf<String>()
        for(i in 1..20){datas.add("Item $i")}

        // TODO : linearLayoutManager, adapter, itemDecoration 설정
        val adapter = MyAdapter(datas)
        binding.recyclerView.adapter = adapter

        val linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = linearLayoutManager

        val itemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        binding.recyclerView.addItemDecoration(itemDecoration)

    }
}


