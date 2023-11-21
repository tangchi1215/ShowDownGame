package org.example.model;


import java.util.List;
import java.util.Random;

public class AIPlayer extends Player {
    private final String name;
    private final Random random = new Random();
    public AIPlayer(String defaultName) {
        this.name = defaultName;
    }

    Player chooseRandomPlayer(List<Player> possiblePlayers) {
        if (possiblePlayers.isEmpty()) {
            return null;
        }
        int randomIndex = random.nextInt(possiblePlayers.size());
        return possiblePlayers.get(randomIndex);
    }

    @Override
    public Card playCard() {
        return getHand().getCards().isEmpty() ? null : getHand().removeCard(0);
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean makeRandomChoice() {
        return random.nextBoolean();
    }
}