package com.muhmmadnabil.alarmapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import kotlin.coroutines.coroutineContext


class AlarmBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, p1: Intent?) {


        val intent=Intent(context,AlarmActivity::class.java)
        intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
        context?.startActivity(intent)

        val serviceIntent = Intent(context, AlarmService::class.java)
        context?.stopService(serviceIntent)


    }
}