/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author Sadula Bineetha
 */
public class Playingcard extends Card{
    public enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES }
    public enum Rank {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8),
        NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14);
        private final int v; Rank(int v){ this.v = v; } public int v(){ return v; }
    }

    private final Suit suit;
    private final Rank rank;

    public Playingcard(Rank rank, Suit suit) {
        this.rank = rank; this.suit = suit;
    }

    public Rank getRank(){ return rank; }
    public Suit getSuit(){ return suit; }

    @Override public int rankValue(){ return rank.v(); }
    @Override public String toString(){ return rank + " of " + suit; }
}
