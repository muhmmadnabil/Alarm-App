package com.muhmmadnabil.alarmapp

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.muhmmadnabil.alarmapp.databinding.ActivityAlarmBinding

class AlarmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAlarmBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val mp = MediaPlayer.create(this, R.raw.music)
        mp.start()

        binding.btnStop.setOnClickListener {
            mp.stop()
        }

    }
}