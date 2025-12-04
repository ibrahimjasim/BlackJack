package com.example.blcakjack.model

/**
 * Represents a standard 52-card deck.
 */
class Deck {

    private val cards: MutableList<Card> = mutableListOf()

    init {
        reset()
    }

    /** Clears and recreates the full deck, then shuffles it. */
    fun reset() {
        cards.clear()
        for (suit in Suit.values()) {
            for (rank in Rank.values()) {
                cards.add(Card(rank, suit))
            }
        }
        cards.shuffle()
    }

    /** Draws (removes + returns) the top card. */
    fun draw(): Card {
        if (cards.isEmpty()) reset()
        return cards.removeAt(0)
    }
}