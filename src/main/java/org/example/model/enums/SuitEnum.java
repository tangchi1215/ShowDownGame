package org.example.model.enums;

public enum SuitEnum {
    CLUBS("♣ Clubs"),
    DIAMONDS("♦ Diamonds"),
    HEARTS("♥ Hearts"),
    SPADES("♠ Spades");

    private final String suitText;

    SuitEnum(String suitText) {
        this.suitText = suitText;
    }

    public String getSuitText() {
        return suitText;
    }
}