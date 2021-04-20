package edu.uw.wensix.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.ericchee.songdataprovider.Song
import edu.uw.wensix.dotify.databinding.ActivityPlayerBinding
import kotlin.random.Random

private const val SONG_KEY = "song"

fun navigateToSongDetail(context: Context, song: Song) = with(context) {

    val intent = Intent(context, PlayerActivity::class.java).apply {
        val bundle = Bundle().apply {
            putParcelable(SONG_KEY, song)
        }
        putExtras(bundle)
    }

    startActivity(intent)
}

class PlayerActivity : AppCompatActivity() {

    private var randomNum = Random.nextInt(0, 10000)
    private lateinit var binding: ActivityPlayerBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        with(binding) {
            val song: Song? = intent.getParcelableExtra<Song>(SONG_KEY)
            if (song?.largeImageID != null) {
                albumCover.setImageResource(song.largeImageID)
            }
            soundTitle.text = song?.title.toString()
            artistName.text = song?.artist.toString()

            numPlayed.text = root.context.getString(R.string.play_format, randomNum)
            playIcon.setOnClickListener { playClicked() }
            previousIcon.setOnClickListener {
                Toast.makeText(
                    this@PlayerActivity,
                    "Skipping to previous track",
                    Toast.LENGTH_SHORT
                ).show()
            }
            nextIcon.setOnClickListener {
                Toast.makeText(this@PlayerActivity, "Skipping to next track", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun playClicked() {
        randomNum += 1
        binding.numPlayed.text = binding.root.context.getString(R.string.play_format, randomNum)
    }
}