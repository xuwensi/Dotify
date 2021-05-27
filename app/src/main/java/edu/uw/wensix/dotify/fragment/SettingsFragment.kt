package edu.uw.wensix.dotify.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.uw.wensix.dotify.DotifyApplication
import edu.uw.wensix.dotify.databinding.FragmentSettingsBinding

const val SONG_NOTIFICATION_ENABLED_PREFS_KEY = "notifications enabled"

class SettingsFragment : Fragment() {

    private val navController by lazy { findNavController() }
    private val safeArgs: SettingsFragmentArgs by navArgs()
    private lateinit var dotifyapp: DotifyApplication
    private val dataRepo by lazy { dotifyapp.dataRepository }
    private lateinit var binding: FragmentSettingsBinding
    private val songNotificationManager by lazy { dotifyapp.notificationManager}
    private val refreshSongManager by lazy {dotifyapp.refreshSongManager}
    private val preferences by lazy { dotifyapp.preferences }

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

            switchNotifications.isChecked = preferences.getBoolean(SONG_NOTIFICATION_ENABLED_PREFS_KEY, false)

            switchNotifications.setOnCheckedChangeListener { _, isChecked ->
                //saves whether checked in preferences
                preferences?.edit {
                    putBoolean(SONG_NOTIFICATION_ENABLED_PREFS_KEY, isChecked)
                }

                if (isChecked) {
                    refreshSongManager.startRefreshSongPeriodically()
                }
            }

        }

        return binding.root
    }


}