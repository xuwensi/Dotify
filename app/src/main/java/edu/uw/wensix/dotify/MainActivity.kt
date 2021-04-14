package edu.uw.wensix.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.*
import edu.uw.wensix.dotify.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var randomNum = Random.nextInt(0, 10000)
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.numPlayed.text = binding.root.context.getString(R.string.play_format, randomNum)
        binding.playIcon.setOnClickListener { playClicked() }
        binding.previousIcon.setOnClickListener {
            Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
        }
        binding.nextIcon.setOnClickListener {
            Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
        }
        binding.changeUser.setOnClickListener { changeUserClicked() }
        binding.applyBtn.setOnClickListener { applyBtnClicked() }
    }

    private fun playClicked() {
        randomNum += 1
        binding.numPlayed.text = binding.root.context.getString(R.string.play_format, randomNum)
    }

    private fun changeUserClicked() {
        binding.username.visibility = INVISIBLE
        binding.changeUser.visibility = INVISIBLE
        binding.editUsername.visibility = VISIBLE
        binding.applyBtn.visibility = VISIBLE

    }

    private fun applyBtnClicked() {
        binding.username.visibility = VISIBLE
        binding.changeUser.visibility = VISIBLE
        binding.editUsername.visibility = INVISIBLE
        binding.applyBtn.visibility = INVISIBLE
        val changedName = binding.editUsername.text.toString()
        binding.username.text = changedName
    }
}