
package com.example.testeopen1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import com.example.testeopen1.AlarmScheduler

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Define the time to open the app (e.g., 9 AM)
        val hour = 13 // Example time to open the app (9 AM)
        val minute = 36 // Example time to open the app (0th minute)

        // Schedule the alarm using the AlarmScheduler
        AlarmScheduler.scheduleAlarm(this, "com.example.OPEN_APP", hour, minute)

        Log.d("MainActivity", "Alarm scheduled for $hour:$minute")
    }
}
