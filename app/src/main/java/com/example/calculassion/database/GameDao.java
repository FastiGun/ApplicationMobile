package com.example.calculassion.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.calculassion.entity.Game;

public class GameDao extends BaseDao<Game>{

    static String INDEX_PSEUDO = "pseudo";
    static String INDEX_SCORE = "score";
    static String INDEX_TENTATIVES = "tentatives";

    public GameDao(DataBaseHelper helper){super(helper);}

    @Override
    protected String getTableName() {
        return "historique";
    }

    @Override
    protected void putValues(ContentValues values, Game entity) {
        values.put(INDEX_PSEUDO,entity.getPseudo());
        values.put(INDEX_SCORE, entity.getScore());
        values.put(INDEX_TENTATIVES, entity.getTentatives());
    }

    @Override
    protected Game getEntity(Cursor cursor) {
        cursor.moveToFirst();
        Game calcul = new Game();
        Integer indexPseudo = cursor.getColumnIndex(INDEX_PSEUDO);
        calcul.setScore(cursor.getString(indexPseudo));
        Integer indexScore = cursor.getColumnIndex(INDEX_SCORE);
        calcul.setScore(cursor.getString(indexScore));
        Integer indexTentatives = cursor.getColumnIndex(INDEX_TENTATIVES);
        calcul.setTentatives(cursor.getString(indexTentatives));
        return calcul;
    }
}
