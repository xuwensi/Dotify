package edu.uw.wensix.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.VISIBLE
import androidx.constraintlayout.widget.ConstraintLayout
import com.ericchee.songdataprovider.SongDataProvider
import edu.uw.wensix.dotify.databinding.ActivitySongListBinding

fun navigateToSongList(context: Context) = with(context) {
    val intent = Intent(context, SongListActivity::class.java)
    startActivity(intent)
}

class SongListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySongListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySongListBinding.inflate(layoutInflater).apply { setContentView(root) }

        title = "All Songs"

        with(binding) {
            val songs = SongDataProvider.getAllSongs()
            val adapter = SongListAdapter(songs)
            allSongList.adapter = adapter
            adapter.songClickedListener = { song ->
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
}