package com.example.casinocardgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.casinocardgame.game.GameSingleton;

public class BetActivity extends AppCompatActivity implements View.OnClickListener {
    //Variables
    GameSingleton gameSingleton = GameSingleton.getInstance();

    //UI
    private Button startGame;
    private Button inceaseBet;
    private Button decreaseBet;
    private TextView currentBet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet);

        //Setting the UI views
        startGame = findViewById(R.id.startGameButton);
        inceaseBet = findViewById(R.id.plusBetButton);
        decreaseBet = findViewById(R.id.minusBetButton);
        currentBet = findViewById(R.id.currentBetText);

        startGame.setOnClickListener(this);
        inceaseBet.setOnClickListener(this);
        decreaseBet.setOnClickListener(this);

        currentBet.setText(gameSingleton.bet + "");
    }

    @Override
    public void onClick(View view) {
        if (view == inceaseBet){
            int newBet = gameSingleton.bet + 10;
            gameSingleton.placeBet(newBet);
            currentBet.setText(gameSingleton.bet + "");
        }
        if (view == decreaseBet){
            int newBet = gameSingleton.bet - 10;
            gameSingleton.placeBet(newBet);
            currentBet.setText(gameSingleton.bet + "");

        }

    }
}

