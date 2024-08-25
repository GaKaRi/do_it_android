package com.example.ch6_3

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.ch6_3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //바인딩 객체 획득
        val binding = ActivityMainBinding.inflate(layoutInflater)

        //액티비티 화면 출력
        setContentView(binding.root)

        //뷰 객체 이요
        binding.visibleBtn.setOnClickListener({
            binding.targetView.visibility = View.VISIBLE
        })
        binding.invisibleBtn.setOnClickListener{
                binding.targetView.visibility = View.INVISIBLE
            }
    }
}