/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;


import java.util.*;

/**
 * The class that models your game. You should create a more specific child of this class and instantiate the methods
 * given.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
public abstract class Game {

   private final String name;
    protected final List<Player> players;

    public Game(String name, List<Player> players){
        this.name = name; this.players = players;
    }
    public String getName(){ return name; }
    public List<Player> getPlayers(){ return players; }

    public abstract void play();
    public abstract void declareWinner();
}

