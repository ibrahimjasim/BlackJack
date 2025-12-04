package com.example.blcakjack.model

/**
 * A Hand is a collection of cards for either the player or the dealer.
 */
class Hand {

    // List of cards currently in this hand
    val cards: MutableList<Card> = mutableListOf()

    /** Adds a card to this hand. */
    fun add(card: Card) {
        cards.add(card)
    }

    /** Removes all cards from this hand (start new round). */
    fun clear() {
        cards.clear()
    }

    /** True if this hand is a natural blackjack (2 cards, total 21). */
    fun isBlackjack(): Boolean = cards.size == 2 && getTotal() == 21

    /** True if total > 21. */
    fun isBust(): Boolean = getTotal() > 21

    /** Calculates the best total for this hand. */
    fun getTotal(): Int {
        var total = 0
        var aces = 0

        for (card in cards) {
            total += card.rank.value
            if (card.rank == Rank.ACE) aces++
        }

        while (total > 21 && aces > 0) {
            total -= 10
            aces--
        }
        return total
    }
}