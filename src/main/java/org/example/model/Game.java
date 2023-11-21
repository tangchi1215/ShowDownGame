package org.example.model;

import org.example.model.event.ExchangeHandsEvent;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Deck deck;
    private final List<Player> players;
    private final List<ExchangeHandsEvent> exchanges;
    private final PlayerInputHandler playerInputHandler = new PlayerInputHandler();

    public Game(int numHumanPlayers) {
        deck = new Deck();
        players = new ArrayList<>();
        exchanges = new ArrayList<>();
        initPlayers(numHumanPlayers);
        setupPlayerNames();
    }

    /**
     * init the number of Players
     *
     * @param numHumanPlayers int
     */
    private void initPlayers(int numHumanPlayers) {
        for (int i = 0; i < numHumanPlayers; i++) {
            players.add(new HumanPlayer("Human Player " + (i + 1)));
        }
        for (int i = numHumanPlayers; i < 4; i++) {
            players.add(new AIPlayer("AI P" + (i + 1)));
        }
    }

    private void setupPlayerNames() {
        int humanPlayerCount = 1;

        for (Player player : players) {
            if (player instanceof HumanPlayer) {

                String name = playerInputHandler.getPlayerName(humanPlayerCount);
                player.setName(name);
                humanPlayerCount++;
            }
        }
    }

    public void start() {
        deck.shuffle();
        drawCards();

        for (int round = 1; round <= 13; round++) {

            playRound(round);

            updateExchanges();
        }
        determineWinner();
        playerInputHandler.close();
    }

    private void drawCards() {
        for (int i = 0; i < 13; i++) {
            for (Player player : players) {
                player.addCard(deck.drawCard());
            }
        }
    }

    private void playRound(int round) {
        System.out.println("-------------------------------------------");
        System.out.println("Round " + round);
        List<Card> playedCards = new ArrayList<>();
        List<String> playSummaries = new ArrayList<>();

        handleExchangeHands();

        for (Player player : players) {

            Card card = player.playCard();
            playedCards.add(card);
            playSummaries.add(player.getName() + " plays: " + card.rank().getRankText() + " of " + card.suit());
        }

        playSummaries.forEach(System.out::println);

        Player roundWinner = determineRoundWinner(playedCards);
        roundWinner.addPoint();
        System.out.println("-------------------------------------------");
        System.out.println("Round winner: " + roundWinner.getName() + " with " + roundWinner.getPoint() + " point(s).");
        System.out.println("===========================================");
    }

    private Player determineRoundWinner(List<Card> playedCards) {
        Card highestCard = playedCards.get(0);
        Player winner = players.get(0);

        for (int i = 1; i < playedCards.size(); i++) {
            Card currentCard = playedCards.get(i);
            if (compareCards(highestCard, currentCard) < 0) {
                highestCard = currentCard;
                winner = players.get(i);
            }
        }

        return winner;
    }

    private void determineWinner() {
        Player gameWinner = players.get(0);
        for (Player player : players) {
            if (player.getPoint() > gameWinner.getPoint()) {
                gameWinner = player;
            }
        }
        System.out.println("Game Winner: " + gameWinner.getName());
        System.out.println("Total Score: "+ gameWinner.getPoint() + " point(s).");
    }

    private int compareCards(Card card1, Card card2) {
        int rankCompare = Integer.compare(card1.rank().getRankValue(), card2.rank().getRankValue());
        if (rankCompare != 0) {
            return rankCompare;
        }
        return card1.suit().compareTo(card2.suit());
    }

    public void exchangeHands(Player currentPlayer, Player targetPlayer) {
        System.out.println("[CHANGE] : "+ currentPlayer.getName() + " changes hand card with "+ targetPlayer.getName());
        ExchangeHandsEvent exchange = new ExchangeHandsEvent(currentPlayer, targetPlayer, 3);
        exchange.executeExchange();
        exchanges.add(exchange);
    }

    private void updateExchanges() {
        exchanges.forEach(ExchangeHandsEvent::update);
        exchanges.removeIf(exchange -> !exchange.isExchanged());


    }

    private void handleExchangeHands() {
        for (Player player : players) {
            if (player instanceof HumanPlayer && player.canExchangeHands()) {
                boolean wantsToExchange = playerInputHandler.askExchange(player.getName());
                if (wantsToExchange) {
                    performExchange(player);
                }
            } else if (player instanceof AIPlayer aiPlayer && player.canExchangeHands()) {
                boolean wantsToExchange = aiPlayer.makeRandomChoice();
                if (wantsToExchange) {
                    Player chosenPlayer = aiPlayer.chooseRandomPlayer(getOtherPlayers(aiPlayer));
                    if (chosenPlayer != null) {
                        exchangeHands(aiPlayer, chosenPlayer);
                        aiPlayer.setHasExchangedHands(true);
                    }
                }
            }
        }
    }


    private void performExchange(Player player) {
        List<Player> possiblePlayers = getOtherPlayers(player);
        Player chosenPlayer = null;

        if (player instanceof HumanPlayer) {
            chosenPlayer = playerInputHandler.chooseExchangePlayer(possiblePlayers);
        } else if (player instanceof AIPlayer aiPlayer) {
            chosenPlayer = aiPlayer.chooseRandomPlayer(possiblePlayers);
        }

        if (chosenPlayer != null) {
            exchangeHands(player, chosenPlayer);
            player.setHasExchangedHands(true);
        }
    }


    private List<Player> getOtherPlayers(Player currentPlayer) {
        return players.stream()
                .filter(p -> p != currentPlayer && p.canExchangeHands())
                .toList();
    }

}
