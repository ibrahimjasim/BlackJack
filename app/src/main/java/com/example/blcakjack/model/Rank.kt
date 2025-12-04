package com.example.blcakjack.model

/**
 * Represents the rank (value) of a card.
 *
 * The 'value' number is used in score calculations.
 */
enum class Rank(val value: Int) {
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6),
    SEVEN(7), EIGHT(8), NINE(9), TEN(10),
    JACK(10), QUEEN(10), KING(10),
    ACE(11)   // ACE can be 11 or 1; Hand.kt handles that
}