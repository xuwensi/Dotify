package edu.uw.wensix.dotify.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ericchee.songdataprovider.Song
import edu.uw.wensix.dotify.DotifyApplication
//import edu.uw.wensix.dotify.SettingsFragmentArgs
//import edu.uw.wensix.dotify.SettingsFragmentDirections
import edu.uw.wensix.dotify.databinding.FragmentSettingsBinding
import kotlinx.coroutines.launch
import kotlin.random.Random


class SettingsFragment : Fragment() {

    private val navController by lazy { findNavController() }

    private val safeArgs: SettingsFragmentArgs by navArgs()

    private lateinit var dotifyapp: DotifyApplication

    private val dataRepo by lazy { dotifyapp.dataRepository }

    private lateinit var binding: FragmentSettingsBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dotifyapp = context.applicationContext as DotifyApplication
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSettingsBinding.inflate(inflater)

        val title = safeArgs.title
        val imgId = safeArgs.imgId
        val playCount = safeArgs.playCount

        with(binding) {
            btnProfile.setOnClickListener {
                navController.navigate(SettingsFragmentDirections.actionSettingsFragmentToProfileFragment())
            }
            btnAbout.setOnClickListener {
                navController.navigate(SettingsFragmentDirections.actionSettingsFragmentToAboutFragment())
            }
            btnStatistic.setOnClickListener {
                navController.navigate(
                    SettingsFragmentDirections.actionSettingsFragmentToStatisticsFragment(
                        playCount,
                        imgId,
                        title
                    )
                )
            }

            switchNotifications.setOnCheckedChangeListener(_, isChecked ) -> {

        }

        }

        return binding.root
    }

    private fun loadSong() {
        lifecycleScope.launch {
            val musicLibrary = dataRepo.getSongLibrary()
            var randomNum = Random.nextInt(0, musicLibrary.numOfSongs + 1)
            dotifyapp.notificationSong = musicLibrary.songs[randomNum]
        }
    }
}