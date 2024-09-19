package com.example.ch8_3

import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ch8_3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //뒤로 가기 버튼을 누른 시각을 저장하는 속성
    var initTime = 0L

    //멈춘 시각을 저장하는 속성
    var pauseTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        //start버튼이 눌렸을때 리스너
        binding.startButton.setOnClickListener{
            binding.chronometer.base = SystemClock.elapsedRealtime() + pauseTime
            binding.chronometer.start()
            //버튼 표시 여부 조정
            binding.stopButton.isEnabled = true //버튼 enable 처리
            binding.resetButton.isEnabled = true
            binding.startButton.isEnabled = false
        }

        //stop버튼이 눌렸을때 리스너
        binding.stopButton.setOnClickListener{
            pauseTime = binding.chronometer.base - SystemClock.elapsedRealtime()
            //현재 시점에서 크로노미터가 시작된 시점을 빼서, 크로노미터가 경과한 시간을 계산합니다.

            binding.chronometer.stop()  //멈춤
            binding.stopButton.isEnabled = false  //버튼 enable 처리
            binding.resetButton.isEnabled = true
            binding.startButton.isEnabled = true
        }

        //reset버튼이 눌렸을때 리스너
        binding.resetButton.setOnClickListener{
            pauseTime = 0L  //초기화함
            binding.chronometer.base = SystemClock.elapsedRealtime()  //시스템 시간을 가져옴
            binding.chronometer.stop()
            binding.stopButton.isEnabled = false //버튼 enable 처리
            binding.resetButton.isEnabled = false
            binding.startButton.isEnabled = true
        }

    }
    //뒤로가기 버튼 핸들러

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        //뒤로가기 버튼을 눌렀을 때 처리
        if (keyCode === KeyEvent.KEYCODE_BACK){

            //뒤로가기 버튼을 처음 눌렀거나 누른지 3초가 지났을 때 처리
            if(System.currentTimeMillis() - initTime > 3000){
                Toast.makeText(this, "종료하려면 한번 더 누르세요",
                    Toast.LENGTH_SHORT).show()
                initTime = System.currentTimeMillis()
                return true
            }
        }

        return super.onKeyDown(keyCode, event)
    }

}
