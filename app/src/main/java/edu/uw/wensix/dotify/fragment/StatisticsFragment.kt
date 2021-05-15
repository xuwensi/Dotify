package edu.uw.wensix.dotify.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import edu.uw.wensix.dotify.DotifyApplication
import edu.uw.wensix.dotify.R
//import edu.uw.wensix.dotify.StatisticsFragmentArgs
import edu.uw.wensix.dotify.databinding.FragmentStatisticsBinding


class StatisticsFragment : Fragment() {

    private val safeArgs: StatisticsFragmentArgs by navArgs()
    lateinit var dotifyApp: DotifyApplication

    override fun onAttach(context: Context) {
        super.onAttach(context)

        dotifyApp = context.applicationContext as DotifyApplication
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentStatisticsBinding.inflate(inflater)

        with(binding) {
            statAlbumCover.setImageResource(safeArgs.imgId)
            val songTitle = safeArgs.title
            val songPlayCount = safeArgs.playCount
            statSongPlayCount.text = root.context.getString(R.string.stat_song_played, songTitle, songPlayCount)
            statTotalPlayedToday.text = root.context.getString(R.string.stat_total_played_sofar, dotifyApp.totalPlayedSoFar)
        }

        return binding.root
    }
}