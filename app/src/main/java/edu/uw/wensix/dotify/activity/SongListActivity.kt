package edu.uw.wensix.dotify.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.VISIBLE
import androidx.constraintlayout.widget.ConstraintLayout
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import edu.uw.wensix.dotify.R
import edu.uw.wensix.dotify.adapter.SongListAdapter
import edu.uw.wensix.dotify.databinding.ActivitySongListBinding

private const val SONG_KEY = "song"

class SongListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySongListBinding

    private var selectedSong: Song? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySongListBinding.inflate(layoutInflater).apply { setContentView(root) }
        title = "All Songs"

        if (savedInstanceState != null) {
            selectedSong = savedInstanceState.getParcelable(SONG_KEY)
            with(binding) {
                miniPlayer.visibility = VISIBLE
                val params = allSongList.layoutParams as ConstraintLayout.LayoutParams
                params.bottomToTop = miniPlayer.id
                allSongList.requestLayout()
                miniPlayerText.text =
                    root.context.getString(
                        R.string.mini_player_format,
                        selectedSong?.title,
                        selectedSong?.artist
                    )
                miniPlayer.setOnClickListener {
                    selectedSong?.let { it1 -> navigateToSongDetail(this@SongListActivity, it1) }
                }
            }
        }

        with(binding) {
            val songs = SongDataProvider.getAllSongs()
            val adapter = SongListAdapter(songs)
            allSongList.adapter = adapter
            adapter.songClickedListener = { song ->
                selectedSong = song
                miniPlayer.visibility = VISIBLE
                val params = allSongList.layoutParams as ConstraintLayout.LayoutParams
                params.bottomToTop = miniPlayer.id
                allSongList.requestLayout()
                miniPlayerText.text =
                    root.context.getString(R.string.mini_player_format, song.title, song.artist)
                miniPlayer.setOnClickListener {
                    navigateToSongDetail(this@SongListActivity, song)
                }
            }
            btnShuffle.setOnClickListener {
                adapter.updateSong(songs.toMutableList().shuffled())
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(SONG_KEY, selectedSong)
        super.onSaveInstanceState(outState)
    }
}