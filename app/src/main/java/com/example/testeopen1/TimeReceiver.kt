package com.example.testeopen1

import android.app.admin.DevicePolicyManager
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import java.util.Calendar

class TimeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Log the received broadcast for debugging
        Log.d("TimeReceiver", "Alarm triggered.")

        // Get the package name dynamically from the intent
        val packageNameToBlock = intent.getStringExtra("packageName") ?: "com.facebook.katana" // Default to Facebook if no package name passed

        // Use DevicePolicyManager to block or unblock apps
        val devicePolicyManager = context.getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        val adminComponent = ComponentName(context, MyDeviceAdminReceiver::class.java)

        if (devicePolicyManager.isAdminActive(adminComponent)) {
            // If the app is a device owner, block or unblock based on time
            if (isRestrictedTime()) {
                // Block app if within restricted time
                blockApp(context, packageNameToBlock)
            } else {
                // Unblock app if within allowed time
                unblockApp(context, packageNameToBlock)
            }
        } else {
            Log.e("TimeReceiver", "Device admin is not active.")
        }
    }

    // Function to check if the current time is within the restricted range
    private fun isRestrictedTime(): Boolean {
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

        // Define restricted time range: Block between 9 PM and 7 AM
        val startHour = 21  // 9 PM
        val endHour = 10  // 7 AM

        return currentHour in startHour..23 || currentHour in 0..endHour
    }

    // Function to block app
    private fun blockApp(context: Context, packageName: String) {
        val devicePolicyManager = context.getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        val adminComponent = ComponentName(context, MyDeviceAdminReceiver::class.java)

        // Block the app by hiding it
        try {
            devicePolicyManager.setApplicationHidden(adminComponent, packageName, true)
            Toast.makeText(context, "$packageName is blocked.", Toast.LENGTH_SHORT).show()
            Log.d("TimeReceiver", "$packageName has been blocked.")
        } catch (e: Exception) {
            Log.e("TimeReceiver", "Failed to block app: ${e.message}")
        }
    }

    // Function to unblock app
    private fun unblockApp(context: Context, packageName: String) {
        val devicePolicyManager = context.getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        val adminComponent = ComponentName(context, MyDeviceAdminReceiver::class.java)

        // Unhide the app
        try {
            devicePolicyManager.setApplicationHidden(adminComponent, packageName, false)
            Toast.makeText(context, "$packageName is unblocked.", Toast.LENGTH_SHORT).show()
            Log.d("TimeReceiver", "$packageName has been unblocked.")
        } catch (e: Exception) {
            Log.e("TimeReceiver", "Failed to unblock app: ${e.message}")
        }
    }
}









