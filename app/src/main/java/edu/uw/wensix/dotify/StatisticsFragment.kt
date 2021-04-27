package edu.uw.wensix.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import edu.uw.wensix.dotify.databinding.FragmentStatisticsBinding


class StatisticsFragment : Fragment() {

    private val safeArgs: StatisticsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentStatisticsBinding.inflate(inflater)

        with(binding) {
            statAlbumCover.setImageResource(safeArgs.imgId)
            val songTitle = safeArgs.title
            val songPlayCount = safeArgs.playCount
            statSongPlayCount.text = root.context.getString(R.string.stat_song_played, songTitle, songPlayCount)
        }

        return binding.root
    }
}