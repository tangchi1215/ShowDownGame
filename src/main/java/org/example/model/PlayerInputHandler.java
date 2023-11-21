package org.example.model;

import java.util.List;
import java.util.Scanner;



public class PlayerInputHandler {
    private final Scanner scanner;

    public PlayerInputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public int getNumOfHumanPlayers() {
        int numHumanPlayers = 0;
        while (numHumanPlayers < 1 || numHumanPlayers > 4) {
            System.out.print("Enter the number of human players (1-4): ");
            if (scanner.hasNextInt()) {
                numHumanPlayers = scanner.nextInt();
                if (numHumanPlayers < 1 || numHumanPlayers > 4) {
                    System.out.println("Invalid number of players.  enter a number (1-4).");
                }
            } else {
                System.out.println("Invalid input. Plz enter a number (1-4).");
                scanner.next();
            }
        }
        return numHumanPlayers;
    }

    public  String getPlayerName(int humanPlayerCount) {
        System.out.print("Enter P" + humanPlayerCount + " name: ");
        return scanner.nextLine();
    }

    public int getShowCard(List<Card> handCards) {
        for (int i = 0; i < handCards.size(); i++) {
            Card card = handCards.get(i);
            String indexFormatted = (i + 1) < 10 ? "0" + (i + 1) : Integer.toString(i + 1);
            System.out.println(indexFormatted + ": " + card.rank().getRankText() + " of " + card.suit());
        }

        System.out.print("Choose a card to play (1-" + handCards.size() + "): ");
        int choice = scanner.nextInt() - 1;

        while (choice < 0 || choice >= handCards.size()) {
            System.out.println("Invalid choice. Plz try again.");
            System.out.print("Choose a card to play (1-" + handCards.size() + "): ");
            choice = scanner.nextInt() - 1;
        }

        return choice;
    }

    public boolean askExchange(String playerName) {
        System.out.print(playerName + ", do you want to exchange hands? (Y/N): ");
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("Y");
    }

    public Player chooseExchangePlayer(List<Player> possiblePlayers) {
        System.out.println("Choose a player to exchange hands with:");
        for (int i = 0; i < possiblePlayers.size(); i++) {
            System.out.println((i + 1) + ": " + possiblePlayers.get(i).getName());
        }
        int choice = readInt(1, possiblePlayers.size()) - 1;
        return possiblePlayers.get(choice);
    }

    public int readInt(int min, int max) {
        while (true) {
            System.out.print("Enter a number (" + min + "-" + max + "): ");
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                scanner.nextLine();
                if (number >= min && number <= max) {
                    return number;
                }
            } else {
                scanner.nextLine();
            }
            System.out.println("Invalid input. Plz enter a number between " + min + " and " + max + ".");
        }
    }

    public void close() {
        scanner.close();
    }
}
