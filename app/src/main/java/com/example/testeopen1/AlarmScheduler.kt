package com.example.testeopen1

import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.app.PendingIntent
import android.util.Log
import android.provider.Settings
import java.util.Calendar

object AlarmScheduler {
    fun scheduleAlarm(context: Context, action: String, hour: Int, minute: Int) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // Check if the app has permission to schedule exact alarms
        if (alarmManager.canScheduleExactAlarms()) {
            val intent = Intent(action).apply { setPackage(context.packageName) }
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                action.hashCode(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            val calendar = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
            }

            try {
                // Schedule the exact alarm
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
                Log.d("AlarmScheduler", "Exact alarm scheduled successfully at $hour:$minute.")
            } catch (e: SecurityException) {
                Log.e("AlarmScheduler", "SecurityException: ${e.message}")
                // Handle the exception gracefully or request permission from the user
            }
        } else {
            // If the permission is not granted, log the message and optionally request permission
            Log.e("AlarmScheduler", "App does not have permission to schedule exact alarms.")
            // Optionally, you can request the user to grant the permission
            requestExactAlarmPermission(context)
        }
    }

    private fun requestExactAlarmPermission(context: Context) {
        val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
        intent.data = android.net.Uri.parse("package:${context.packageName}")
        context.startActivity(intent)
    }
}
