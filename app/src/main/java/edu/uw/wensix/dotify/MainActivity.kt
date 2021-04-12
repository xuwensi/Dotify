package edu.uw.wensix.dotify

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.*
import edu.uw.wensix.dotify.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var randomNum = Random.nextInt(0, 10000)
    private lateinit var numPlayed: TextView
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.numPlayed.text = "$randomNum plays"
        binding.playIcon.setOnClickListener { playClicked() }
        binding.previousIcon.setOnClickListener { previousClicked() }
        binding.nextIcon.setOnClickListener { nextClicked() }
        binding.changeUser.setOnClickListener { changeUserClicked() }
        binding.applyBtn.setOnClickListener { applyBtnClicked() }
    }

    @SuppressLint("SetTextI18n")
    fun playClicked() {
        randomNum += 1
        binding.numPlayed.text = "$randomNum plays"
    }

    private fun previousClicked() {
        Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
    }

    private fun nextClicked() {
        Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
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