package com.xjkwak.broadcastreceiverexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.util.Log
import android.widget.TextView

//TextView passed in from the MainActivity
class SMSReceiver(val messageScreen: TextView) : BroadcastReceiver() {

    val TAG = "SMSReceiver"
    override fun onReceive(context: Context, intent: Intent) {
        Log.i(TAG, "Received!")
        val bundle = intent.extras

        var text = "Received msg: "
        if (bundle != null) { //we have something to pull out

            val format = bundle.getString("format")
            val pdus = bundle["pdus"] as Array<Any>?
            if (pdus != null) {

                for (i in pdus.indices) {

                    // If Android version M or newer (which ours is since we work with min API 23):
                    val msg  = SmsMessage.createFromPdu(pdus[i] as ByteArray, format)

                    text += "From ${msg.originatingAddress} : ${msg.messageBody}"
                }
            }
        }

        messageScreen.text = """
                ${messageScreen.text}
                $text
                """.trimIndent()

    }
}