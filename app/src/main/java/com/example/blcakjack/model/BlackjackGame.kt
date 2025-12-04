package com.example.blcakjack.model

/**
 * Core game engine for a simple Blackjack:
 * - One player vs one dealer
 * - Supports Hit, Stand, Double Down
 * - Handles chips and payouts
 */
class BlackjackGame {

    private val deck = Deck()

    // Player and dealer hands
    val playerHand = Hand()
    val dealerHand = Hand()

    // Player's chip count (money)
    var chips: Int = 1000
        private set

    // How much the player is betting this round
    var currentBet: Int = 50
        private set

    // True when the round has ended (someone stood or bust)
    var roundOver: Boolean = false
        private set

    /**
     * Starts a fresh round:
     * - Reset hands
     * - Place bet (subtract from chips)
     * - Deal two cards to player and dealer
     * - Check for immediate blackjack
     */
    fun startNewRound() {
        roundOver = false

        // Clear hands from previous round
        playerHand.clear()
        dealerHand.clear()

        // Take the bet from player's chips (if enough chips)
        if (chips >= currentBet) {
            chips -= currentBet
        } else {
            // If not enough chips, set bet to 0 (you could also end the game here)
            currentBet = 0
        }

        // Initial deal: player, dealer, player, dealer
        playerHand.add(deck.draw())
        dealerHand.add(deck.draw())
        playerHand.add(deck.draw())
        dealerHand.add(deck.draw())

        // If either got a blackjack right away, the round ends immediately
        if (playerHand.isBlackjack() || dealerHand.isBlackjack()) {
            finishRound()
        }
    }

    /** Player presses "Hit" → draw one card. */
    fun hit() {
        if (roundOver) return
        playerHand.add(deck.draw())
        if (playerHand.isBust()) {
            finishRound()
        }
    }

    /** Player presses "Stand" → dealer plays, then we evaluate. */
    fun stand() {
        if (roundOver) return

        // Dealer must draw until total is at least 17
        while (dealerHand.getTotal() < 17) {
            dealerHand.add(deck.draw())
        }

        finishRound()
    }

    /** Player presses "Double Down". */
    fun doubleDown() {
        if (roundOver || chips < currentBet) return

        // Double the bet
        chips -= currentBet
        currentBet *= 2

        // Player gets exactly one more card
        playerHand.add(deck.draw())

        // If player didn't bust, dealer also plays
        if (!playerHand.isBust()) {
            while (dealerHand.getTotal() < 17) {
                dealerHand.add(deck.draw())
            }
        }

        finishRound()
    }

    /**
     * Handles the end of the round:
     * - Compare totals
     * - Pay out chips for win / blackjack / push
     */
    private fun finishRound() {
        roundOver = true

        val playerTotal = playerHand.getTotal()
        val dealerTotal = dealerHand.getTotal()

        when {
            // Player has a natural blackjack and dealer does NOT
            playerHand.isBlackjack() && !dealerHand.isBlackjack() -> {
                // Blackjack usually pays 3:2 → bet * 2.5
                chips += (currentBet * 2.5).toInt()
            }

            // Player busts → loses bet
            playerHand.isBust() -> {
                // nothing, bet already subtracted
            }

            // Dealer busts or player closer to 21 than dealer
            dealerHand.isBust() || playerTotal > dealerTotal -> {
                // Normal win → player gets 2x bet (bet back + profit)
                chips += currentBet * 2
            }

            // Equal totals → push → bet returned
            playerTotal == dealerTotal -> {
                chips += currentBet
            }

            // Else: dealer wins, player loses bet
        }
    }
}