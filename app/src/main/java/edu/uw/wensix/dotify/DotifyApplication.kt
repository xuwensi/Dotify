package edu.uw.wensix.dotify

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.ericchee.songdataprovider.Song
import edu.uw.wensix.dotify.manager.RefreshSongManager
import edu.uw.wensix.dotify.manager.SongNotificationManager
import edu.uw.wensix.dotify.repository.DataRepository

const val SONG_PREFS_KEY = "song prefs"

class DotifyApplication: Application() {

    lateinit var dataRepository: DataRepository
    var totalPlayedSoFar: Int = 0
    var notificationSong: Song? = null
    lateinit var notificationManager: SongNotificationManager
    lateinit var refreshSongManager: RefreshSongManager
    lateinit var preferences: SharedPreferences

    override fun onCreate() {
        super.onCreate()

        dataRepository = DataRepository()
        this.notificationManager = SongNotificationManager(this)
        this.refreshSongManager = RefreshSongManager(this)
        this.preferences = getSharedPreferences(SONG_PREFS_KEY, Context.MODE_PRIVATE)
    }

}
