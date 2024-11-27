package com.example.testeopen1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log




// TimeReceiver.kt
class TimeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            "com.example.OPEN_APP" -> {
                val launchIntent = context.packageManager.getLaunchIntentForPackage(context.packageName)
                launchIntent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) // Ensures app opens in a new task
                context.startActivity(launchIntent)
                Log.d("TimeReceiver", "App opened.")
            }
        }
    }
}


