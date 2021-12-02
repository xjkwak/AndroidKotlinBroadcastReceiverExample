package com.xjkwak.broadcastreceiverexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.telephony.NetworkRegistrationInfo
import android.widget.Toast

class MyBroadcastReceiver:BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        // intent contains the information about the broadcast

        when (intent?.action) {
            Intent.ACTION_POWER_CONNECTED -> Toast.makeText(context, "Power Connected!", Toast.LENGTH_LONG).show()
            Intent.ACTION_POWER_DISCONNECTED -> Toast.makeText(context, "Power Disconnected!", Toast.LENGTH_LONG).show()
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                val isAirplaneModeEnabled = intent?.getBooleanExtra("state", false) ?: return
                if (isAirplaneModeEnabled) {
                    // showing the toast message if airplane mode is enabled
                    Toast.makeText(context, "Airplane Mode Enabled", Toast.LENGTH_LONG).show()
                } else {
                    // showing the toast message if airplane mode is disabled
                    Toast.makeText(context, "Airplane Mode Disabled", Toast.LENGTH_LONG).show()
                }
            }

            WifiManager.WIFI_STATE_CHANGED_ACTION -> {
                val wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN)

                if (wifiState == WifiManager.WIFI_STATE_ENABLED || wifiState == WifiManager.WIFI_STATE_ENABLING) {
                    Toast.makeText(context, "WIFI :)", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "WIFI :(", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
