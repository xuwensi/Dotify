package edu.uw.wensix.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.uw.wensix.dotify.databinding.FragmentAboutBinding


class AboutFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentAboutBinding.inflate(inflater)

        with(binding) {
            versionNum.text = root.context.getString(R.string.about_version_number, BuildConfig.VERSION_NAME.toString())
        }

        return binding.root
    }
}