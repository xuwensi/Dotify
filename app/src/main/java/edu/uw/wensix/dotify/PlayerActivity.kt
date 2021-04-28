package edu.uw.wensix.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.ericchee.songdataprovider.Song
import android.view.Menu
import android.view.MenuItem
import edu.uw.wensix.dotify.databinding.ActivityPlayerBinding
import kotlin.random.Random

private const val SONG_KEY = "song"
private const val COUNT_VALUE_KEY = "countValue"

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

    private var songTitle: String = ""
    private var imgId: Int = 0

    private lateinit var binding: ActivityPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            randomNum = savedInstanceState.getInt(COUNT_VALUE_KEY, 0)
        }

        binding = ActivityPlayerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        with(binding) {
            val song: Song? = intent.getParcelableExtra(SONG_KEY)

            if (song != null) {
                songTitle = song.title
                imgId = song.largeImageID
            }

            albumCover.setImageResource(song?.largeImageID ?: return)
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
            btnSetting.setOnClickListener {
                navigateToSetting(
                    this@PlayerActivity,
                    song.title,
                    song.largeImageID,
                    randomNum.toString()
                )
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.setting_menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_setting -> {
            navigateToSetting(
                this@PlayerActivity,
                songTitle,
                imgId,
                randomNum.toString()
            )
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
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

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(COUNT_VALUE_KEY, randomNum)
        super.onSaveInstanceState(outState)
    }
}