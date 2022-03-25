package com.example.calculassion.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculassion.R;
import com.example.calculassion.database.GameBaseHelper;
import com.example.calculassion.database.GameDao;
import com.example.calculassion.entity.Game;
import com.example.calculassion.service.GameService;

public class Jeu extends AppCompatActivity {

    private TextView textViewResultat;
    private TextView textViewExercice;
    private TextView textViewScore;
    private EditText editText;
    private ConstraintLayout layoutFond;
    private String pseudo = "";
    private Integer resultat = 0;
    private Integer reponse = 0;
    private Integer score = 0;
    private Integer tentatives = 1;
    private GameService gameService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);
        textViewResultat = findViewById(R.id.textViewResultat);
        textViewExercice = findViewById(R.id.textViewExercice);
        textViewScore = findViewById(R.id.textViewScore);
        editText = findViewById(R.id.editText);
        gameService = new GameService(new GameDao(new GameBaseHelper(this)));
        layoutFond = findViewById(R.id.layoutFond);
        Question();

        Button bouton1 = findViewById(R.id.buttonNum1);
        bouton1.setOnClickListener(view -> ajouteValeur(1));

        Button bouton2 = findViewById(R.id.buttonNum2);
        bouton2.setOnClickListener(view -> ajouteValeur(2));

        Button bouton3 = findViewById(R.id.buttonNum3);
        bouton3.setOnClickListener(view -> ajouteValeur(3));

        Button bouton4 = findViewById(R.id.buttonNum4);
        bouton4.setOnClickListener(view -> ajouteValeur(4));

        Button bouton5 = findViewById(R.id.buttonNum5);
        bouton5.setOnClickListener(view -> ajouteValeur(5));

        Button bouton6 = findViewById(R.id.buttonNum6);
        bouton6.setOnClickListener(view -> ajouteValeur(6));

        Button bouton7 = findViewById(R.id.buttonNum7);
        bouton7.setOnClickListener(view -> ajouteValeur(7));

        Button bouton8 = findViewById(R.id.buttonNum8);
        bouton8.setOnClickListener(view -> ajouteValeur(8));

        Button bouton9 = findViewById(R.id.buttonNum9);
        bouton9.setOnClickListener(view -> ajouteValeur(9));

        Button bouton0 = findViewById(R.id.buttonNum0);
        bouton0.setOnClickListener(view -> ajouteValeur(0));

        Button boutonDelete = findViewById(R.id.buttonDelete);
        boutonDelete.setOnClickListener(view -> {textViewResultat.setText(""); resultat = 0;});

        Button boutonMinus = findViewById(R.id.buttonMinus);
        boutonMinus.setOnClickListener(view -> signeChange());

        Button boutonValidate = findViewById(R.id.buttonValidate);
        boutonValidate.setOnClickListener(view -> lancerReponse());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        MenuItem toolbarFinish = menu.findItem(R.id.toolBarFinish);
        toolbarFinish.setOnMenuItemClickListener(menuItem -> gameEnd());
        return true;
    }

    private void ajouteValeur(Integer valeur){
        if (resultat >= 0)
            resultat = resultat * 10 + valeur;
        else
            resultat = resultat * 10 - valeur;
        textViewResultat.setText(resultat.toString());
    }

    private void signeChange(){
        resultat = -resultat;
        textViewResultat.setText(resultat.toString());
    }

    private void Question(){
        Integer choixOperation = (int) (Math.random() * 3);
        Integer num1 = (int) (Math.random() * 20) + 1;
        Integer num2 = (int) (Math.random() * 20) + 1;
        if (choixOperation < 1){
            reponse = num1 + num2;
            textViewExercice.setText(num1.toString() + " + " + num2.toString());
            return;
        }
        if (choixOperation < 2){
            reponse = num1 - num2;
            textViewExercice.setText(num1.toString() + " - " + num2.toString());
            return;
        }
        if (choixOperation < 3){
            Integer n1 = num1 / 2;
            Integer n2 = num2 / 2;
            reponse = n1 * n2;
            textViewExercice.setText(n1.toString() + " * " + n2.toString());
            return;
        }
        else{
            reponse = num1 + num2;
            textViewExercice.setText(num1.toString() + " + " + num2.toString());
            return;
        }
    }

    private void lancerReponse(){
        textViewResultat.setText("");
        if(reponse == resultat){
            flash(layoutFond, Color.rgb(59, 179, 55));
            score++;
            if (score < 2) {
                textViewScore.setText(score.toString() + getString(R.string.nbGoodAnswer3));
            }else{
                textViewScore.setText(score.toString() + getString(R.string.nbGoodAnswer2));
            }
            //Toast.makeText(this, "Vrai", Toast.LENGTH_SHORT).show();
        }
        else{
            flash(layoutFond, Color.rgb(181,53,53));
            //Toast.makeText(this, "Faux", Toast.LENGTH_SHORT).show();
            if (score < 2) {
                textViewScore.setText(score.toString() + getString(R.string.nbGoodAnswer3));
            }else{
                textViewScore.setText(score.toString() + getString(R.string.nbGoodAnswer2));
            }
        }
        resultat = 0;
        reponse = 0;
        tentatives++;
        textViewResultat.setText("");
        Question();
    }

    private void flash(ConstraintLayout layout, int color){
        int colorFrom = color;
        int colorTo = Color.BLACK;
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(350); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                layout.setBackgroundColor((int) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
    }

    private boolean gameEnd(){
        pseudo = editText.getText().toString();
        Game game = new Game();
        game.setPseudo(pseudo);
        game.setScore(score.toString());
        game.setTentatives(tentatives.toString());
        gameService.storeGame(game);
        editText.setText("");
        tentatives = 0;
        resultat = 0;
        textViewScore.setText(getString(R.string.nbGoodAnswer));
        startActivity(new Intent(this, Scores.class));
        return true;
    }
}