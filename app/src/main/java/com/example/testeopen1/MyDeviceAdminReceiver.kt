package com.example.testeopen1

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MyDeviceAdminReceiver : DeviceAdminReceiver() {

    override fun onEnabled(context: Context, intent: Intent) {
        super.onEnabled(context, intent)
        // Log to show device admin is enabled
        Log.d("MyDeviceAdminReceiver", "Device Admin Enabled")

        // You can add any additional logic when the admin is enabled,
        // for instance, starting a background task or registering a receiver for time-based blocking
    }

    override fun onDisabled(context: Context, intent: Intent) {
        super.onDisabled(context, intent)
        // Log to show device admin is disabled
        Log.d("MyDeviceAdminReceiver", "Device Admin Disabled")

        // Optionally, you can clean up or reset settings when the admin is disabled
    }

    // Optional: You can implement other methods for more device admin-related features.
    // For example, you could add methods for locking the device or wiping data (if desired).
}
