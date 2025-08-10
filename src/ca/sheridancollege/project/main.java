/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author Sadula Bineetha
 */
public class main {
    
    public static void main(String[] args) {
        // Create players (names will be asked interactively in GoFishGame)
        GoFishPlayer player1 = new GoFishPlayer("");
        GoFishPlayer player2 = new GoFishPlayer("");

        // Create and run the game
        GoFishGame game = new GoFishGame(player1, player2);
        game.play();
    }
}
