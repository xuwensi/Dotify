package edu.uw.wensix.dotify

import android.app.Application
import edu.uw.wensix.dotify.repository.DataRepository

class DotifyApplication: Application() {

    lateinit var dataRepository: DataRepository
    var totalPlayedSoFar: Int = 0

    override fun onCreate() {
        super.onCreate()

        dataRepository = DataRepository()
    }
}