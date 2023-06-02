package com.example.casinocardgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.casinocardgame.API.ApiConnection;
import com.example.casinocardgame.game.GameSingleton;

public class BetActivity extends AppCompatActivity implements View.OnClickListener {
    //Variables
    GameSingleton gameSingleton = GameSingleton.getInstance();
    ApiConnection apiConnection = ApiConnection.getInstance();

    //UI
    private Button startGame;
    private Button inceaseBet;
    private Button decreaseBet;
    private TextView currentBet;

    private TextView currentBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet);

        //Setting the UI views
        startGame = findViewById(R.id.startGameButton);
        inceaseBet = findViewById(R.id.plusBetButton);
        decreaseBet = findViewById(R.id.minusBetButton);
        currentBet = findViewById(R.id.currentBetText);
        currentBalance = findViewById(R.id.currentBalanceText);

        startGame.setOnClickListener(this);
        inceaseBet.setOnClickListener(this);
        decreaseBet.setOnClickListener(this);

        currentBet.setText(gameSingleton.bet + "");
        Double balance = apiConnection.getPlayerBalance();
        System.out.println(balance);
        currentBalance.setText(apiConnection.balance + "");
    }

    @Override
    public void onClick(View view) {
        double newBet = gameSingleton.bet;
        if (view == inceaseBet) newBet = gameSingleton.bet + 10;
        if (view == decreaseBet) newBet = gameSingleton.bet - 10;
        if (newBet <= apiConnection.balance) {
            gameSingleton.placeBet(newBet);
            currentBet.setText(gameSingleton.bet + "");
        }

        if (view == startGame && gameSingleton.bet <= apiConnection.balance ){
            Intent gameIntent = new Intent(this, GameActivity.class);
            startActivity(gameIntent);
        }

    }
}

