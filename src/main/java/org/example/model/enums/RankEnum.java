package org.example.model.enums;

public enum RankEnum {
    TWO("2",2),
    THREE("3",3),
    FOUR("4",4 ),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 11),
    QUEEN("Q", 12),
    KING("K", 13),
    ACE("A", 14);

    private final String rankText;
    private final int rankValue;



    RankEnum(String rankText, int rankValue) {
        this.rankText = rankText;
        this.rankValue = rankValue;
    }

    public String getRankText() {
        return rankText;
    }

    public int getRankValue() {
        return rankValue;
    }
}
