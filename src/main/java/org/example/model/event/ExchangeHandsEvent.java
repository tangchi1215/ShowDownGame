package org.example.model.event;

import org.example.model.Hand;
import org.example.model.Player;

public class ExchangeHandsEvent {
    private final Player currentPlayer;
    private final Player targetPlayer;
    private int roundsRemaining;
    private boolean isExchanged;

    public ExchangeHandsEvent(Player currentPlayer, Player targetPlayer, int roundsRemaining) {
        this.currentPlayer = currentPlayer;
        this.targetPlayer = targetPlayer;
        this.roundsRemaining = roundsRemaining;
        this.isExchanged = false;
    }

    public void executeExchange() {
        if (!isExchanged) {
            Hand tempHand = currentPlayer.getHand();
            currentPlayer.setHand(targetPlayer.getHand());
            targetPlayer.setHand(tempHand);
            isExchanged = true;
        }
    }

    public void update() {
        if (isExchanged) {
            roundsRemaining--;
            if (roundsRemaining <= 0) {
                revertExchange();
            }
        }
    }

    private void revertExchange() {
        System.out.println("[REVERT] : " + currentPlayer.getName() + " and " + targetPlayer.getName() + " revert their hands back.");
        Hand tempHand = currentPlayer.getHand();
        currentPlayer.setHand(targetPlayer.getHand());
        targetPlayer.setHand(tempHand);
        isExchanged = false;
    }

    public boolean isExchanged() {
        return isExchanged && roundsRemaining > 0;
    }
}
