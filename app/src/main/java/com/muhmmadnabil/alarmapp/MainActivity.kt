package com.muhmmadnabil.alarmapp

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.muhmmadnabil.alarmapp.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSetAlarm.setOnClickListener {
            val hours = binding.timePicker.hour
            val minutes = binding.timePicker.minute

            binding.tvTime.text = "$hours:$minutes"

            val seconds = binding.edSec.text.toString().toInt()


            val intent = Intent(this, AlarmService::class.java)
            intent.putExtra(SECOND_REQUEST_CODE, seconds)
            //startService(intent)

            ContextCompat.startForegroundService(this, intent)

            Toast.makeText(
                applicationContext,
                "Alarm is set for $seconds seconds",
                Toast.LENGTH_LONG
            )
                .show()


        }

    }

    companion object {
        const val SECOND_REQUEST_CODE = "SECONDS"
    }


}