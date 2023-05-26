package com.example.casinocardgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.casinocardgame.API.ApiConnection;
import com.example.casinocardgame.game.GameSingleton;

public class EndActivity extends AppCompatActivity implements View.OnClickListener {
    //Variables
    GameSingleton gameSingleton = GameSingleton.getInstance();
    ApiConnection apiConnection = ApiConnection.getInstance();

    //UI
    private Button restartGame;
    private TextView resultText;
    private TextView currentBalance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        currentBalance = findViewById(R.id.currentBalanceTextEnd);
        resultText = findViewById(R.id.gameResultTextView);
        restartGame = findViewById(R.id.restartGameButton);
        restartGame.setOnClickListener(this);

        if(gameSingleton.gameWon){
            resultText.setText("Congratulations! You've won "+gameSingleton.bet+" game tokens!");
        } else {
            resultText.setText("Too bad. You've lost "+gameSingleton.bet+" game tokens.");
        }
        gameSingleton.gameOver();

        currentBalance.setText(apiConnection.balance + "");
    }

    @Override
    public void onClick(View view) {
        if (view == restartGame){
            Intent betIntent = new Intent(this, BetActivity.class);
            startActivity(betIntent);
        }

    }
}