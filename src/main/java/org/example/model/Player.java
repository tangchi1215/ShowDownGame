package org.example.model;

import java.util.List;

public abstract class Player {
    private Hand hand;
    private int point = 0;
    private boolean hasExchanged;
    protected Player() {
        this.hand = new Hand();
        this.hasExchanged = false;
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public abstract Card playCard();

    public void setName(String name) {
    }

    public abstract String getName();

    public void addPoint() {
        this.point++;
    }

    public int getPoint() {
        return point;
    }

    public List<Card> getHandCards() {
        return hand.getCards();
    }

    public boolean canExchangeHands() {
        return !hasExchanged;
    }

    public void setHasExchangedHands(boolean hasExchanged) {
        this.hasExchanged = hasExchanged;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }


}
