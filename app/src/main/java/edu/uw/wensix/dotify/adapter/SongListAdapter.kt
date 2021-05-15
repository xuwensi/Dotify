package edu.uw.wensix.dotify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song
import edu.uw.wensix.dotify.SongDiffCallback
import edu.uw.wensix.dotify.databinding.ItemSongBinding


class SongListAdapter(private var listOfSongs: List<Song>) :
    RecyclerView.Adapter<SongListAdapter.SongHolder>() {

    var songClickedListener: (song: Song) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        val itemBinding = ItemSongBinding.inflate(LayoutInflater.from(parent.context))
        return SongHolder(itemBinding)

    }

    override fun getItemCount(): Int = listOfSongs.size

    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        val song = listOfSongs[position]
        with(holder.binding) {
            songPic.setImageResource(song.smallImageID)
            songTitle.text = song.title
            songArtist.text = song.artist
            itemRoot.setOnClickListener {
                songClickedListener(song)
            }
        }
    }

    fun updateSong(newList: List<Song>) {
        val callback = SongDiffCallback(newList, listOfSongs)
        val result = DiffUtil.calculateDiff(callback)
        result.dispatchUpdatesTo(this)

        this.listOfSongs = newList
    }

    class SongHolder(val binding: ItemSongBinding) : RecyclerView.ViewHolder(binding.root)
}