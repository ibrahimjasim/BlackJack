package com.example.blcakjack.model

/**
 * Represents a 52-card deck.
 * - Creates 52 cards
 * - Shuffles them
 * - Allows drawing cards
 */
class Deck {

    private val cards: MutableList<Card> = mutableListOf()

    init {
        reset()
    }

    /** Rebuilds the deck and shuffles it. */
    fun reset() {
        cards.clear()

        for (suit in Suit.values()) {
            for (rank in Rank.values()) {
                cards.add(Card())
            }
        }

        cards.shuffle()
    }

    /** Draws and removes the top card. If empty, rebuilds deck. */
    fun draw(): Card {
        if (cards.isEmpty()) reset()
        return cards.removeAt(0)
    }
}