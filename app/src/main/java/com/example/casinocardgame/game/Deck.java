package com.example.casinocardgame.game;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    public List<Card> activeDeck;
    public List<Card> inactiveDeck;
    public Card cardback;

    public Deck(){
        List<String> suits = new ArrayList<>();
        suits.add("c"); //clover
        suits.add("d"); //diamond
        suits.add("h"); //heart
        suits.add("s"); //spade

        for (String suit : suits ) {
            for (int value = 1; value < 14; value++) {
                Card newCard = new Card(suit, value);
                activeDeck.add(newCard);
            }
        }

        Card cardback = new Card("cardback", 0);
        cardback.name = "cardback";
        this.cardback = cardback;
    }

    private int getRandomCardIndex() {
        int min = 0;
        int max = this.activeDeck.size()-1;
        return (int) ((Math.random() * (max - min)) + min);
    }

    public Card drawCard(){
        int index = getRandomCardIndex();
        if(!activeDeck.isEmpty()){
            Card drawnCard = activeDeck.remove(index);
            this.inactiveDeck.add(drawnCard);
            return drawnCard;
        }
        return null;
    }

    public Card getHiddenCard(){
        return this.cardback;
    }

    public List<Card> getInactiveCards(){
        return this.inactiveDeck;
    }

}
