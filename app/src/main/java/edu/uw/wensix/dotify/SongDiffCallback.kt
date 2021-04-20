package edu.uw.wensix.dotify

import androidx.recyclerview.widget.DiffUtil
import com.ericchee.songdataprovider.Song

class SongDiffCallback(private val newSong: List<Song>, private val oldSong: List<Song>) :
    DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newSong = newSong[newItemPosition]
        val oldSong = oldSong[oldItemPosition]
        return newSong.id == oldSong.id
    }

    override fun getOldListSize(): Int = oldSong.size

    override fun getNewListSize(): Int = newSong.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newSong = newSong[newItemPosition]
        val oldSong = oldSong[oldItemPosition]
        return newSong.title == oldSong.title && newSong.artist == oldSong.artist &&
                newSong.largeImageID == oldSong.largeImageID &&
                newSong.smallImageID == oldSong.smallImageID
    }


}