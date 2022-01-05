package com.muhmmadnabil.alarmapp

import android.app.*
import android.content.Intent
import android.content.Context
import android.os.IBinder
import androidx.core.app.NotificationCompat

import android.app.PendingIntent
import android.os.Build
import kotlin.concurrent.thread
import android.app.NotificationManager

import android.app.NotificationChannel
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName


class AlarmService : Service() {
    var seconds: Int = 0

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        seconds = intent?.getIntExtra(MainActivity.SECOND_REQUEST_CODE, 0)!!

        thread {
            kotlin.run {

                val intent = Intent(this, AlarmBroadcastReceiver::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                val pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0)
                val am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                am.set(
                    AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis() + (seconds * 1000),
                    pendingIntent
                )
            }
        }


        val notificationIntent = Intent(applicationContext, MainActivity::class.java)
        val pendingIntent2: PendingIntent = PendingIntent.getActivity(
            applicationContext,
            0, notificationIntent, 0
        )

        val notification: Notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(this, "channel_id")
                .setContentTitle("Alarm App")
                .setContentText("The app is running")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent2)
                .setTicker("Ticker text")
                .build()
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        val mgr = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
            mgr.getNotificationChannel("channel_id") == null
        ) {
            mgr.createNotificationChannel(
                NotificationChannel(
                    "channel_id",
                    "Whatever", NotificationManager.IMPORTANCE_DEFAULT
                )
            )

        }
        startForeground(1, notification)
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}

