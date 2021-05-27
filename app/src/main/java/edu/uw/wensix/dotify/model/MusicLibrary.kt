package edu.uw.wensix.dotify.model

import android.os.Parcel
import android.os.Parcelable
import com.ericchee.songdataprovider.Song
import kotlinx.parcelize.Parcelize

@Parcelize
data class MusicLibrary (
    val title: String,
    val numOfSongs: Int,
    val songs: List<Song>
): Parcelable {
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }
}