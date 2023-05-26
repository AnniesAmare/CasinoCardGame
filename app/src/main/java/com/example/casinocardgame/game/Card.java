package com.example.casinocardgame.game;

import java.util.Locale;

public class Card implements Comparable<Card>{
    public String name;
    public String suit;
    public int value;

    public Card (String suit, int value) {
        this.suit = suit;
        this.value = value;

        String suitName = suit.toLowerCase();
        String valueName = (value < 10 ) ? "0" + value : "" + value;
        this.name = suitName + valueName;

        //testing
        //System.out.println(this.name);
    }

    public String getName(){
        return this.name;
    }

    @Override
    public int compareTo(Card card) {
        //  0: if (x==y)
        //  -1: if (x < y)
        //  1: if (x > y)
        return Integer.compare(this.value, card.value);
    }
}
