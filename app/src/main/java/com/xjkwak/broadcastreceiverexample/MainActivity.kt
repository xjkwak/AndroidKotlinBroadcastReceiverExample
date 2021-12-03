package com.xjkwak.broadcastreceiverexample

import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.telephony.SmsManager

class MainActivity : AppCompatActivity() {
    // register the receiver in the main activity in order
    // to receive updates of broadcasts events if they occur
    lateinit var receiver: MyBroadcastReceiver
    lateinit var receiver2: SMSReceiver

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

        val incomingSmsIntentFilter = IntentFilter("android.provider.Telephony.SMS_RECEIVED")
        receiver2 = SMSReceiver(findViewById(R.id.salida)) //tv refers to the TextView on the UI
        registerReceiver(receiver2, incomingSmsIntentFilter)

    }

    // since AirplaneModeChangeReceiver class holds a instance of Context
    // and that context is actually the activity context in which
    // the receiver has been created
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
        unregisterReceiver(receiver2)
    }
}