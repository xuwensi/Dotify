package edu.uw.wensix.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.ericchee.songdataprovider.Song
import edu.uw.wensix.dotify.databinding.ActivitySettingsBinding

private const val TITLE_KEY = "TITLE_KEY"
private const val IMG_ID_KEY = "IMG_ID_KEY"
private const val PLAY_COUNT_KEY = "PLAY_COUNT_KEY"

fun navigateToSetting(context: Context, title: String, imgId: Int, playCount: String) = with(context) {
    val intent = Intent(context, SettingsActivity::class.java).apply {
        val bundle = Bundle().apply {
            putString(TITLE_KEY, title)
            putString(PLAY_COUNT_KEY, playCount)
            putInt(IMG_ID_KEY, imgId)
        }
        putExtras(bundle)
    }

    startActivity(intent)
}

class SettingsActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.navHost) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val title = intent.extras?.getString(TITLE_KEY)
        val imgId = intent.extras?.getInt(IMG_ID_KEY)
        val playCount = intent.extras?.getString(PLAY_COUNT_KEY)

        navController.setGraph(R.navigation.nav_graph, Bundle().apply {
            putString("title", title)
            putString("playCount", playCount)
            if (imgId != null) {
                putInt("imgId", imgId)
            }
        })
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp() = navController.navigateUp()
}