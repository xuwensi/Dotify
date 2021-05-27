package edu.uw.wensix.dotify.manager

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

private const val SONG_SYNC_WORK_TAG = "SONG_SYNC_WORK_TAG"

class RefreshSongManager(context: Context) {
    private val workManager = WorkManager.getInstance(context)

    fun refreshSongs() {
        val request = OneTimeWorkRequestBuilder<SongWorker>()
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .addTag(SONG_SYNC_WORK_TAG)
            .build()

        workManager.enqueue(request)
    }

    fun startRefreshSongPeriodically() {
        if (isSongRefreshing()) {
            return
        }

        val request = PeriodicWorkRequestBuilder<SongWorker>(20, TimeUnit.MINUTES)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .addTag(SONG_SYNC_WORK_TAG)
            .build()

        workManager.enqueue(request)
    }

    fun stopPeriodicallyRefreshing() {
        workManager.cancelAllWorkByTag(SONG_SYNC_WORK_TAG)
    }

    private fun isSongRefreshing(): Boolean {
        return workManager.getWorkInfosByTag(SONG_SYNC_WORK_TAG).get().any {
            when (it.state) {
                WorkInfo.State.RUNNING,
                WorkInfo.State.ENQUEUED -> true
                else -> false
            }
        }
    }


}