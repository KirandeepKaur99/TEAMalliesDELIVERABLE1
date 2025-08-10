/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.*;
import java.util.Collections;

/**
 * A concrete class that represents any grouping of cards for a Game. HINT, you might want to subclass this more than
 * once. The group of cards has a maximum size attribute which is flexible for reuse.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
public class GroupOfCards {
     private final List<Card> cards = new ArrayList<>();

    public int size(){ return cards.size(); }
    public boolean isEmpty(){ return cards.isEmpty(); }

    public void add(Card c){ cards.add(c); }
    public void addAll(Collection<? extends Card> cs){ cards.addAll(cs); }
    public boolean remove(Card c){ return cards.remove(c); }

    /** Remove and return all cards of the given rank. */
    public List<Card> removeAllOfRank(Playingcard.Rank rank){
        List<Card> out = new ArrayList<>();
        Iterator<Card> it = cards.iterator();
        while (it.hasNext()){
            Playingcard pc = (Playingcard) it.next();
            if (pc.getRank() == rank){ out.add(pc); it.remove(); }
        }
        return out;
    }

       public Card draw(){ return isEmpty() ? null : cards.remove(0); }

    public void shuffle(){ Collections.shuffle(cards); }

    /** Read-only view so callers can inspect the hand. */
    public List<Card> getCards(){ return Collections.unmodifiableList(cards); }

    @Override public String toString(){ return cards.toString(); }
}
