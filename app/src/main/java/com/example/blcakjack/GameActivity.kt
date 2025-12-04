package com.example.blcakjack  // keep this if the rest of your app uses blcakjack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.blcakjack.databinding.ActivityGameBinding
import com.example.blcakjack.model.BlackjackGame
import com.example.blcakjack.model.Card   // for cardsToString()

class GameActivity : AppCompatActivity() {

    // ViewBinding for activity_game.xml
    private lateinit var binding: ActivityGameBinding

    // Our game engine
    private lateinit var game: BlackjackGame

    // Name passed from MainActivity
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

        setupButtons()
        startRound()
    }

    private fun setupButtons() {
        binding.btnHit.setOnClickListener {
            game.hit()          // needs hit() in BlackjackGame
            updateUI()
        }

        binding.btnStand.setOnClickListener {
            game.stand()        // needs stand() in BlackjackGame
            updateUI()
        }

        binding.btnDoubleDown.setOnClickListener {
            game.doubleDown()   // needs doubleDown() in BlackjackGame
            updateUI()
        }

        binding.btnNewRound.setOnClickListener {
            startRound()        // calls our own startRound()
        }
    }

    private fun startRound() {
        game.startNewRound()    // needs startNewRound() in BlackjackGame
        updateUI()
    }

    private fun updateUI() {
        // Dealer
        binding.tvDealerCards.text = cardsToString(game.dealerHand.cards)
        binding.tvDealerScore.text = game.dealerHand.getTotal().toString()

        // Player
        binding.tvPlayerCards.text = cardsToString(game.playerHand.cards)
        binding.tvPlayerScore.text = game.playerHand.getTotal().toString()

        // Chips
        binding.tvChips.text = "Chips: ${game.chips}"

        // Only allow new round when finished
        binding.btnNewRound.isEnabled = game.roundOver
    }

    // Helper to show cards like "10♣  A♠"
    private fun cardsToString(cards: List<Card>): String {
        return cards.joinToString("  ") { it.display() }
    }
}