package com.example.casinocardgame.game;

import com.example.casinocardgame.API.ApiConnection;

public class GameSingleton {

    private static GameSingleton instance;
    public Deck deck;
    public int bet;
    public int minBet = 10;
    public int maxBet = 250;

    public boolean gameWon = false;


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

    public void setGameWon(Boolean result){
        this.gameWon = result;
    }

    public void gameOver(){
        ApiConnection api = ApiConnection.getInstance();
        api.processGameOver(bet, gameWon);
        this.gameWon = false;
        this.bet = minBet;
    }


}
