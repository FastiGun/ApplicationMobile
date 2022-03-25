package com.example.calculassion.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.calculassion.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button play = findViewById(R.id.main_play);
        play.setOnClickListener(view -> {
            startActivity(new Intent(this, Jeu.class));
        });

        Button scores = findViewById(R.id.scores);
        scores.setOnClickListener(view -> {
            startActivity(new Intent(this, Scores.class));
        });

        Button apropos = findViewById(R.id.apropos);
        apropos.setOnClickListener(view -> {
            startActivity(new Intent(this, Apropos.class));
        });
    }


}