package org.example.model;


import java.util.List;

public class HumanPlayer extends Player {
    PlayerInputHandler playerInputHandler = new PlayerInputHandler();
    private String name;

    public HumanPlayer(String name) {
        super();
        this.name = name;
    }

    @Override
    public Card playCard() {
        System.out.println(getName() + ", your hand: ");

        List<Card> handCards = getHandCards();
        int choice = playerInputHandler.getShowCard(handCards);
        return getHand().removeCard(choice);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
