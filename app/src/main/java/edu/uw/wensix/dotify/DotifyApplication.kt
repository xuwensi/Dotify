package edu.uw.wensix.dotify

import android.app.Application
import com.ericchee.songdataprovider.Song
import edu.uw.wensix.dotify.manager.SongNotificationManager
import edu.uw.wensix.dotify.repository.DataRepository

class DotifyApplication: Application() {

    lateinit var dataRepository: DataRepository
    var totalPlayedSoFar: Int = 0
    lateinit var notificationSong: Song
    lateinit var notificationManager: SongNotificationManager

    override fun onCreate() {
        super.onCreate()

        dataRepository = DataRepository()
        this.notificationManager = SongNotificationManager(this)
    }
}