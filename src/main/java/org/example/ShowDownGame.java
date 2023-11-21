package org.example;


import org.example.model.Game;
import org.example.model.PlayerInputHandler;


public class ShowDownGame {
    public static void main(String[] args) {
        PlayerInputHandler playerInputHandler = new PlayerInputHandler();

        System.out.println("~ Welcome to the Showdown Poker Game ~");

        int numHumanPlayers = playerInputHandler.getNumOfHumanPlayers();

        Game game = new Game(numHumanPlayers);
        game.start();
    }
}

