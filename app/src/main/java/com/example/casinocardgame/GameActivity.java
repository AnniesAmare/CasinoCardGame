package com.example.casinocardgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.casinocardgame.game.Card;
import com.example.casinocardgame.game.GameSingleton;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    //Variables
    GameSingleton gameSingleton = GameSingleton.getInstance();
    Boolean gameOver = false;
    int guessCount = 0;

    //UI
    private Button gameOverButton;
    private Button biggerThan;
    private Button lesserThan;
    private Button equal;
    private TextView currentBet;

    private ImageView card1View;
    private Card card1;
    private ImageView card2View;
    private Card card2;
    private ImageView card3View;
    private Card card3;
    private ImageView cardFocusView;
    private Card card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameOverButton = findViewById(R.id.gameOverButton);
        gameOverButton.setOnClickListener(this);

        biggerThan = findViewById(R.id.biggerThanButton);
        biggerThan.setOnClickListener(this);

        lesserThan = findViewById(R.id.smallerThanButton);
        lesserThan.setOnClickListener(this);

        equal = findViewById(R.id.equalButton);
        equal.setOnClickListener(this);

        card1View = findViewById(R.id.cardImage1);
        card2View = findViewById(R.id.cardImage2);
        card3View = findViewById(R.id.cardImage3);
        cardFocusView = findViewById(R.id.cardImageFocus);

        currentBet = findViewById(R.id.betContentText);
        currentBet.setText(gameSingleton.bet + "");

        card = gameSingleton.deck.drawCard();
        setCardView(card, cardFocusView);

    }

    @Override
    public void onClick(View view) {
        if (!gameOver){
            guessCount++;
            if (guessCount == 1){
                setCardView(card, card1View);
                card1 = gameSingleton.deck.drawCard();
                setCardView(card1, cardFocusView);
                gameOver = isWrongGuess(view, card, card1);
            }
            if (guessCount == 2){
                setCardView(card1, card2View);
                card2 = gameSingleton.deck.drawCard();
                setCardView(card2, cardFocusView);
                gameOver = isWrongGuess(view, card1, card2);
            }
            if (guessCount == 3){
                setCardView(card2, card3View);
                card3 = gameSingleton.deck.drawCard();
                setCardView(card3, cardFocusView);
                gameOver = isWrongGuess(view, card2, card3);
                if (!gameOver){
                    gameSingleton.setGameWon(true);
                    gameOver = true;
                }
            }
            if (gameOver){
                biggerThan.setVisibility(view.GONE);
                lesserThan.setVisibility(view.GONE);
                equal.setVisibility(view.GONE);
                gameOverButton.setVisibility(view.VISIBLE);
            }
        }
        if (view == gameOverButton){
            Intent endIntent = new Intent(this, EndActivity.class);
            startActivity(endIntent);
        }

    }


    private void setCardView(Card card, ImageView cardView){
        String cardname = card.getName();
        int drawableID = getResources().getIdentifier(cardname, "drawable", "com.example.casinocardgame");
        cardView.setImageResource(drawableID);
    }

    private Boolean isWrongGuess(View guess, Card card, Card drawnCard){
        int result = card.compareTo(drawnCard);

        if (guess == lesserThan && result == 1) return false;
        if (guess == biggerThan && result == -1) return false;
        if (guess == equal && result == 0) return false;
        return true;

    }
}