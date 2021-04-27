package edu.uw.wensix.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.uw.wensix.dotify.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    private val navController by lazy { findNavController() }

    private val safeArgs: SettingsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSettingsBinding.inflate(inflater)

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
                navController.navigate(SettingsFragmentDirections.actionSettingsFragmentToStatisticsFragment(playCount, imgId, title))
            }
        }

        return binding.root
    }
}