package edu.uw.wensix.dotify.manager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import edu.uw.wensix.dotify.DotifyApplication

class SongWorker (
    private val context: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(context, workerParameters) {

    private val dotifyApp by lazy { context.applicationContext as DotifyApplication }
    private val songNotificationManager by lazy { dotifyApp.notificationManager }

    override suspend fun doWork(): Result {
//        songNotificationManager.
        return Result.success()
    }

}