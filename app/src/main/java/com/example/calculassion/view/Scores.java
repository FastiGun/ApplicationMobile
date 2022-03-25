package com.example.calculassion.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.calculassion.R;
import com.example.calculassion.database.GameBaseHelper;
import com.example.calculassion.database.GameDao;
import com.example.calculassion.service.GameService;

public class Scores extends AppCompatActivity {

    private GameService gameService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        gameService = new GameService(new GameDao(new GameBaseHelper(this)));

        TextView textViewBest1 = findViewById(R.id.textViewBest1);
        TextView textViewScore1 = findViewById(R.id.textViewScore1);

        TextView textViewBest2 = findViewById(R.id.textViewBest2);
        TextView textViewScore2 = findViewById(R.id.textViewScore2);

        TextView textViewBest3 = findViewById(R.id.textViewBest3);
        TextView textViewScore3 = findViewById(R.id.textViewScore3);

        Long nombreLignes = gameService.getPlayerNumber();

        if (nombreLignes > 0) {
            textViewBest1.setText(gameService.best1());
            textViewScore1.setText(gameService.bestScore1());
        }

        if (nombreLignes > 1) {
            textViewBest2.setText(gameService.best2());
            textViewScore2.setText(gameService.bestScore2());
        }

        if (nombreLignes > 2) {
            textViewBest3.setText(gameService.best3());
            textViewScore3.setText(gameService.bestScore3());
        }
    }
}