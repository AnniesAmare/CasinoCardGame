package com.example.casinocardgame.game;

public class GameSingleton {

    private static GameSingleton instance;
    public Deck deck;
    public int bet;
    public int minBet = 10;
    public int maxBet = 250;

    public String playerName;

    public static GameSingleton getInstance(){
        if (instance == null){
            instance = new GameSingleton();
            instance.createDeck();
            instance.placeBet(instance.minBet);
        }
        return instance;
    }

    private void createDeck(){
        Deck deck = new Deck();
        this.deck = deck;
    }

    public void placeBet(int bet){
        if (bet >= minBet && bet <= maxBet){
            this.bet = bet;
        }
    }


}
