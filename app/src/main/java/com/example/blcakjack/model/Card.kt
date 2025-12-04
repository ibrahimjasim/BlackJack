package com.example.blcakjack.model

/**
 * A single playing card with a rank and a suit.
 */
data class Card(
    val rank: Rank,
    val suit: Suit
) {
    /**
     * Returns a short text like "A♠", "10♦", "K♥"
     * used to display the card in the UI.
     */
    fun display(): String {
        val rankSymbol = when (rank) {
            Rank.ACE -> "A"
            Rank.JACK -> "J"
            Rank.QUEEN -> "Q"
            Rank.KING -> "K"
            else -> rank.value.toString()
        }

        val suitSymbol = when (suit) {
            Suit.CLUBS -> "♣"
            Suit.DIAMONDS -> "♦"
            Suit.HEARTS -> "♥"
            Suit.SPADES -> "♠"
        }

        return "$rankSymbol$suitSymbol"
    }
}