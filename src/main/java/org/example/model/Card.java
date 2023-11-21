package org.example.model;

import org.example.model.enums.RankEnum;
import org.example.model.enums.SuitEnum;

public record Card(RankEnum rank, SuitEnum suit) {

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
