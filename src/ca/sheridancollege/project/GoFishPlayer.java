/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.*;

/**
 *
 * @author Sadula Bineetha
 */
public class GoFishPlayer extends Player{
    
    private final GroupOfCards hand = new GroupOfCards();
    private int books = 0;

    public GoFishPlayer(String name) { super(name); }

    public void addCard(Card c) { hand.add(c); }
    public void addCards(Collection<? extends Card> cs) { hand.addAll(cs); }
    public GroupOfCards getHand() { return hand; }
    public int getBooks() { return books; }

    public boolean hasRank(Playingcard.Rank rank) {
        return hand.getCards().stream()
                .anyMatch(c -> ((Playingcard) c).getRank() == rank);
    }

    /** Give ALL cards of that rank to the asker (and remove them from hand). */
    public List<Card> giveAllOfRank(Playingcard.Rank rank) {
        return hand.removeAllOfRank(rank);
    }

    /** Remove any completed books (4 of a kind) and increment score. */
    public void checkForBooks() {
        Map<Playingcard.Rank, Integer> counts = new EnumMap<>(Playingcard.Rank.class);
        for (Card c : hand.getCards()) {
            Playingcard pc = (Playingcard) c;
             counts.put(pc.getRank(), counts.getOrDefault(pc.getRank(), 0) + 1);
        }
        for (Map.Entry<Playingcard.Rank, Integer> e : counts.entrySet()) { // <-- explicit type, no 'var'
            if (e.getValue() == 4) {
                hand.removeAllOfRank(e.getKey());
                books++;
                System.out.println("📚 " + getName() + " completed a book of " + e.getKey() + "s!");
            }
        }
    }

    @Override
    public void play() { /* handled in GoFishGame */ }
}
