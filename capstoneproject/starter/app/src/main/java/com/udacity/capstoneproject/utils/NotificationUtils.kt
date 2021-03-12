package com.udacity.capstoneproject.utils

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.udacity.capstoneproject.MainActivity
import com.udacity.capstoneproject.R

fun NotificationManager.sendDataUpdateNotification(
        applicationContext: Context,
        notificationID: Int
) {
    // Create the content intent for the notification
    val contentIntent = Intent(applicationContext, MainActivity::class.java)

    // pending intent that is pending until triggered from the builder
    val contentPendingIntent = PendingIntent.getActivity(
            applicationContext,
            notificationID,
            contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
    )


    val downloadImage = BitmapFactory.decodeResource(
            applicationContext.resources,
            R.drawable.covidimg
    )

    // style of the notification
    val bigPicStyle = NotificationCompat.BigPictureStyle()
            .bigPicture(downloadImage)
            .bigLargeIcon(null)

    // builder to Build the notification
    val builder = NotificationCompat.Builder(
            applicationContext,
            MainActivity.CHANNEL_ID
    )
            .setSmallIcon(R.drawable.covidimg)
            .setContentTitle(applicationContext.getString(R.string.covid_data_updated))
            .setAutoCancel(true)
            .setStyle(bigPicStyle) // when the users swipes/drags the individual notif. down the big picture will be seen
            .setLargeIcon(downloadImage)
            .addAction(
                    R.drawable.covidimg,
                    applicationContext.getString(R.string.see_latest_updates),
                    contentPendingIntent
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    notify(notificationID, builder.build())
}
