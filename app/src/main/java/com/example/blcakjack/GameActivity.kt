package com.example.blcakjack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.blcakjack.databinding.ActivityGameBinding
import com.example.blcakjack.model.BlackjackGame

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var game: BlackjackGame
    private var playerName: String = "Player"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Get name from previous screen
        playerName = intent.getStringExtra("player_name") ?: "Player"
        binding.tvPlayerLabel.text = "$playerName's Hand"

        // Init game engine
        game = BlackjackGame()



    }





}