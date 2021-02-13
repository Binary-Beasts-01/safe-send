package com.example.safesend

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.provider.Telephony
import android.telephony.SmsMessage
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService

class MessageReceivedBroadcastReceiver : BroadcastReceiver() {

    val CHANNEL_ID = "channelId"
    val CHANNEL_NAME = "channelName"

    override fun onReceive(context: Context?, intent: Intent?) {
       for (sms in Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
           createNotificationChannel(context)
           showNotification(context, sms)
       }
    }

    private fun showNotification(context: Context?, sms: SmsMessage) {
        val notification = context?.let {
            NotificationCompat.Builder(it, CHANNEL_ID)
                .setContentTitle(sms.originatingAddress)
                .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.profile_pic))
                .setContentText(sms.displayMessageBody)
                .setSmallIcon(R.drawable.ic_info_about)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build()
        }


        val notificationManager = context?.let { NotificationManagerCompat.from(it) }
        if (notification != null) {
            notificationManager?.notify(0, notification)
        }
    }

    private fun createNotificationChannel(context: Context?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val chanel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH).apply {
                lightColor = Color.GREEN
                enableLights(true)
            }
            val manager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(chanel)
        }
    }

}