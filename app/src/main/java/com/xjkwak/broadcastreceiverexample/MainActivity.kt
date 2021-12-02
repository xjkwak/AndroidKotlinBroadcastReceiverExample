package com.xjkwak.broadcastreceiverexample

import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    // register the receiver in the main activity in order
    // to receive updates of broadcasts events if they occur
    lateinit var receiver: MyBroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        receiver = MyBroadcastReceiver()

        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
        registerReceiver(receiver, filter)
    }

    // since AirplaneModeChangeReceiver class holds a instance of Context
    // and that context is actually the activity context in which
    // the receiver has been created
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}