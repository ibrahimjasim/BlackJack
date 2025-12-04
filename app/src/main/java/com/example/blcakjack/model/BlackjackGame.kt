package com.example.blcakjack.model

class BlackjackGame {
    private val deck = Deck()

    // Player and dealer hands
    private val playerHand = Hand()
    private val dealerHand = Hand()

    // Player's chips count
    var chips = 1000
        private set

    // How much the player is betting this round
    var currentBet: Int = 50
        private set

    // True when the round has ended (someone stood or bust)
    var roundOver: Boolean = false
        private set



    fun startNewRound() {
        roundOver = false

        // Clear hands from previous round
        playerHand.clear()
        dealerHand.clear()

        // Take the bet from player's chips (if enough chips)
        if (chips >= currentBet) {
            chips -= currentBet

        } else {
            // If not enough chips, set bet to 0
            currentBet = 0
        }
    }

}
