package com.udacity.capstoneproject.work

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.capstoneproject.Constants
import com.udacity.capstoneproject.MainActivity.Companion.CHANNEL_ID
import com.udacity.capstoneproject.R
import com.udacity.capstoneproject.database.getDatabase
import com.udacity.capstoneproject.repository.CountryRepository
import com.udacity.capstoneproject.utils.sendDataUpdateNotification
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters): CoroutineWorker(appContext, params) {
    companion object {
        const val WORK_NAME = "RefreshAsteroidWorker"
    }

    private lateinit var notificationManager: NotificationManager

    // should do the following every 15 minutes
    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = CountryRepository(database)

        return try {
            repository.refreshGlobalStats()
            repository.refreshCountries()
            // initializing notification manager
            notificationManager =  getSystemService(applicationContext, NotificationManager::class.java)!!
            // creating a channel to send notifications to through
            createNotificationChannel(
                CHANNEL_ID, // const variable from companion object
                applicationContext.getString(R.string.notification_channel),
                applicationContext.getString(R.string.notification_channnel_description),
                false,
                true
            )
            notificationManager.sendDataUpdateNotification(applicationContext, Constants.RefreshDataWorkerNotificationId)
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }

    private fun createNotificationChannel(id: String, name: String, description: String, enableLights: Boolean, enableVibration: Boolean){
        // create a channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(id, name,
                NotificationManager.IMPORTANCE_DEFAULT)
                .apply {
                    setShowBadge(false)
                }

            notificationChannel.enableLights(enableLights)
            notificationChannel.enableVibration(enableVibration)
            notificationChannel.description = description

            notificationManager.createNotificationChannel(notificationChannel)

        }
    }
}