package edu.uw.wensix.dotify.manager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import edu.uw.wensix.dotify.DotifyApplication
import kotlin.random.Random

class SongWorker (
    private val context: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(context, workerParameters) {

    private val dotifyApp by lazy { context.applicationContext as DotifyApplication }
    private val songNotificationManager by lazy { dotifyApp.notificationManager }
    private val dataRepo by lazy { dotifyApp.dataRepository }

    override suspend fun doWork(): Result {
        val musicLibrary = dataRepo.getSongLibrary()
        var randomNum = Random.nextInt(0, musicLibrary.songs.size)
        dotifyApp.notificationSong = musicLibrary.songs[randomNum]

        songNotificationManager.publishSongNotification()

        return Result.success()
    }


}
