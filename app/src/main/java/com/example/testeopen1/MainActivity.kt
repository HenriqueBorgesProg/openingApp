package com.example.testeopen1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    // Define the time range during which the app cannot be accessed
    private val startHour = 22 // 22:00 (10 PM)
    private val endHour =   6 // 06:00 (6 AM)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if the current time is within the restricted time range
        if (isRestrictedTime()) {
            // Show a message and close the activity
            Toast.makeText(this, "O aplicativo não pode ser acessado neste horário.", Toast.LENGTH_LONG).show()
            finish() // Close the Activity
            return
        }

        // If it's within the allowed time, load the Activity normally
        setContentView(R.layout.activity_main)

        // Schedule the alarm to block the app at 10 PM (22:00)
        val hour = 11 // Block at 10 PM
        val minute = 0
        AlarmScheduler.scheduleAlarm(this, "com.example.OPEN_APP", hour, minute)
    }

    // Function to check if the current time is within the restricted range
    private fun isRestrictedTime(): Boolean {
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)

        // Check if the current time is within the restricted time range
        return if (startHour < endHour) {
            currentHour in startHour until 24 || currentHour in 0 until endHour
        } else {
            currentHour in startHour until endHour
        }
    }
}
