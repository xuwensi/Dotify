package edu.uw.wensix.dotify.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.ericchee.songdataprovider.Song
import edu.uw.wensix.dotify.R
import edu.uw.wensix.dotify.databinding.ActivitySettingsBinding

private const val TITLE_KEY = "title"
private const val IMG_ID_KEY = "imgId"
private const val PLAY_COUNT_KEY = "playCount"

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
        navController.setGraph(R.navigation.nav_graph, intent.extras)

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp() = navController.navigateUp()
}