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
public class GoFishGame extends Game{
    
     private final GoFishPlayer p1;
    private final GoFishPlayer p2;
    private GroupOfCards deck; // game composes a deck

    public GoFishGame(GoFishPlayer p1, GoFishPlayer p2){
        super("Go Fish", Arrays.asList(p1, p2));
        this.p1 = p1; this.p2 = p2;
    }

    @Override
    public void play(){
        Scanner in = new Scanner(System.in);

        // Ask names if blank
        if (p1.getName()==null || p1.getName().isBlank()){
            System.out.print("Enter Player 1 name: "); p1.setName(in.nextLine().trim());
        }
        if (p2.getName()==null || p2.getName().isBlank()){
            System.out.print("Enter Player 2 name: "); p2.setName(in.nextLine().trim());
        }
        
System.out.println("\n=== Welcome to Go Fish, " + p1.getName() + " & " + p2.getName() + "! ===");
        System.out.println(
            "Rules:\n" +
            "1) Ask your opponent for a rank (ACE..KING).\n" +
            "2) If they have it, they give ALL cards of that rank; you go again.\n" +
            "3) Otherwise: \"Go Fish!\" and you draw one card.\n" +
            "4) If you draw the asked rank, you go again.\n" +
            "5) Four of a rank = a BOOK (1 point).\n"
        );

        // Build 52-card deck and shuffle
        deck = new GroupOfCards();
        for (Playingcard.Suit s : Playingcard.Suit.values())
            for (Playingcard.Rank r : Playingcard.Rank.values())
                deck.add(new Playingcard(r, s));
        deck.shuffle();

        // Deal 7 each
        for (int i=0; i<7; i++){ p1.addCard(deck.draw()); p2.addCard(deck.draw()); }
        p1.checkForBooks(); p2.checkForBooks();

        GoFishPlayer current = p1, other = p2;

        while (!deck.isEmpty() && (p1.getHand().size()>0 || p2.getHand().size()>0)) {

            // Auto-draw if current has no cards but deck still has some
            if (current.getHand().isEmpty() && !deck.isEmpty()){
                Card c = deck.draw();
                if (c != null){
                    current.addCard(c);
                    System.out.println(current.getName() + " had no cards and drew: " + c);
                    current.checkForBooks();
                }
                }
            if (current.getHand().isEmpty()){
                // swap so other can act, or exit if nobody can
                GoFishPlayer tmp = current; current = other; other = tmp;
                if (current.getHand().isEmpty() && deck.isEmpty()) break;
                continue;
            }

            System.out.println("\n----- " + current.getName() + "'s turn -----");
            System.out.println("Your hand: " + current.getHand());
            System.out.println("Your ranks: " + readableRanks(current.getHand()));

            System.out.print("Enter a rank to ask for (e.g., ACE, TEN, JACK), or 'q' to quit: ");
            String input = in.nextLine().trim().toUpperCase();
            if (input.equals("Q")) { System.out.println("Game aborted."); declareWinner(); return; }

            Playingcard.Rank rank;
            try { rank = Playingcard.Rank.valueOf(input); }
            catch (IllegalArgumentException ex){ System.out.println("Invalid rank. Try again."); continue; }

            if (!current.hasRank(rank)){
                System.out.println("Tip: usually you ask for a rank you already hold. We'll allow it.");
            }

            boolean extraTurn = false;

            if (other.hasRank(rank)){
                List<Card> taken = other.giveAllOfRank(rank);
                current.addCards(taken);
                System.out.println(other.getName() + " gives " + taken.size() + " card(s) of " + rank + "!");
                extraTurn = true;
            } else {
                System.out.println(other.getName() + " says: \"Go Fish!\"");
                Card drawn = deck.draw();
                if (drawn != null){
                    current.addCard(drawn);
                    System.out.println("You drew: " + drawn);
                    if (drawn instanceof Playingcard) {
                        Playingcard pc = (Playingcard) drawn;
                        if (pc.getRank() == rank) {
                            System.out.println("Lucky! You drew the rank you asked for — take another turn.");
                            extraTurn = true;
                        }
                    }
                    current.checkForBooks();
                } else {
                    System.out.println("Deck is empty.");
                }
            }

            System.out.printf("Book— %s: %d | %s: %d%n",
                    p1.getName(), p1.getBooks(), p2.getName(), p2.getBooks());

            if (!extraTurn){
                GoFishPlayer tmp = current; current = other; other = tmp;
            }
        }

        declareWinner();
    }

    private String readableRanks(GroupOfCards hand){
        EnumMap<Playingcard.Rank, Integer> map = new EnumMap<>(Playingcard.Rank.class);
        for (Card c : hand.getCards()){
            Playingcard pc = (Playingcard) c;
            map.put(pc.getRank(), map.getOrDefault(pc.getRank(), 0) + 1);
        }
        return map.isEmpty() ? "(none)" : map.toString();
    }
    @Override
    public void declareWinner(){
        System.out.println("\n=== Game Over ===");
        System.out.printf("\t completed %d book(s).%n", p1.getName(), p1.getBooks());
        System.out.printf("\t completed %d book(s).%n", p2.getName(), p2.getBooks());
        if (p1.getBooks() > p2.getBooks()) System.out.println(p1.getName() + " wins!");
        else if (p2.getBooks() > p1.getBooks()) System.out.println(p2.getName() + " wins!");
        else System.out.println("It's a tie!");
    }
}