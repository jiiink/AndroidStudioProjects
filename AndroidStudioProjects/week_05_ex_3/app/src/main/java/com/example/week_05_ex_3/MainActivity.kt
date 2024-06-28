package com.example.week_05_ex_3.week5_ex3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.week_05_ex_3.MyPagerAdapter
import com.example.week_05_ex_3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val datas = mutableListOf<String>()
        for(i in 1..3){datas.add("Item $i")}

        // TODO : 뷰페이저에 어댑터를 연결, 방향 설정
        val adapter = MyPagerAdapter(datas)

        binding.viewpager.adapter = adapter

        binding.viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }
}

