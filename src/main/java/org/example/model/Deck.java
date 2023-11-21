package org.example.model;

import org.example.model.enums.RankEnum;
import org.example.model.enums.SuitEnum;

import java.util.Collections;
import java.util.Stack;


public class Deck {
    private Stack<Card> cards;

    public Deck() {
        cards = new Stack<>();
        for (SuitEnum suit : SuitEnum.values()) {
            for (RankEnum rank : RankEnum.values()) {
                cards.push(new Card(rank, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return cards.isEmpty() ? null : cards.pop();
    }
}

