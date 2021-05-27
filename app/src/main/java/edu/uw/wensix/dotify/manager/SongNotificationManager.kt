package edu.uw.wensix.dotify.manager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ericchee.songdataprovider.Song
import edu.uw.wensix.dotify.DotifyApplication
import edu.uw.wensix.dotify.R
import edu.uw.wensix.dotify.activity.PlayerActivity
import edu.uw.wensix.dotify.activity.SONG_KEY
import kotlin.random.Random

private const val SONG_NOTIFICATION_CHANNEL_ID = "New Uploaded Music"

class SongNotificationManager (private val context: Context){
    private val notificationManager = NotificationManagerCompat.from(context)
    private val dotifyApp: DotifyApplication = context.applicationContext as DotifyApplication

    init {
        initNotificationChannels()
    }

    fun publishSongNotification() {
        var songSelected: Song? = dotifyApp.notificationSong

        // Create an explicit intent for an Activity in your app
        val intent = Intent(context, PlayerActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(SONG_KEY, songSelected)
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)


        val builder = NotificationCompat.Builder(context, SONG_NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_song)
            .setContentTitle("${songSelected?.artist} just released a new song!!!")
            .setContentText("Listen to ${songSelected?.title} now on Dotify")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(notificationManager) {
            // notificationId is a unique int for each notification that you must define
            val notificationId = Random.nextInt()
            notify(notificationId, builder.build())
        }
    }

    private fun initNotificationChannels() {
        createSongChannel()
    }

    private fun createSongChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Info of channel
            val name = context.getString(R.string.song_channel_name)
            val descriptionText = context.getString(R.string.song_channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(SONG_NOTIFICATION_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            // Register the channel with the system
            notificationManager.createNotificationChannel(channel)
        }
    }
}