package edu.uw.wensix.dotify.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import coil.load
import edu.uw.wensix.dotify.DotifyApplication
import edu.uw.wensix.dotify.R
import edu.uw.wensix.dotify.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private lateinit var dotifyApp: DotifyApplication
    private val dataRepo by lazy { dotifyApp.dataRepository }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dotifyApp = context.applicationContext as DotifyApplication
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentProfileBinding.inflate(inflater)

        lifecycleScope.launch {
            val user = dataRepo.getUser()
            with(binding) {
                imgUser.load(user.profilePicURL)
                userName.text = user.username
                userFirstName.text = root.context.getString(R.string.profile_first_name, user.firstName)
                userLastName.text = root.context.getString(R.string.profile_last_name, user.lastName)
                userHasNose.text = root.context.getString(R.string.profile_has_nose, user.hasNose)
                userPlatform.text = root.context.getString(R.string.profile_platform, user.platform)
            }
        }


        return binding.root
    }
}