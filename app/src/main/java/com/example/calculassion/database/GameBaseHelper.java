package com.example.calculassion.database;

import android.content.Context;

public class GameBaseHelper extends DataBaseHelper{

    public GameBaseHelper(Context context) {
        super(context, "Games", 1);
    }

    @Override
    protected String getCreationSql() {
        String script = "create table if not exists historique (" +
                "id integer primary key autoincrement, " +
                GameDao.INDEX_PSEUDO + " varchar(255) not null, " +
                GameDao.INDEX_SCORE + " varchar(255) not null, " +
                GameDao.INDEX_TENTATIVES + " varchar(255) not null" +
                ")";
        return script;
    }

    @Override
    protected String getDeleteSql() {
        return null;
    }
}
