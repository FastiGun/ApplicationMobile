package com.example.calculassion.service;

import com.example.calculassion.database.GameDao;
import com.example.calculassion.entity.BaseEntity;
import com.example.calculassion.entity.Game;

import java.util.List;

public class GameService {

    private GameDao gameDao;

    public GameService(GameDao calculDao) {
        this.gameDao = calculDao;
    }

    public long getGameNumber(){
        return gameDao.count();
    }

    public void storeGame(Game game){
        gameDao.create(game);
    }

    public Long getPlayerNumber(){return gameDao.count();}

    public String best1(){return gameDao.best1();}

    public String best2(){return gameDao.best2();}

    public String best3(){return gameDao.best3();}

    public String bestScore1(){return gameDao.bestScore1();}

    public String bestScore2(){return gameDao.bestScore2();}

    public String bestScore3(){return gameDao.bestScore3();}

    //public String bestScore1(){return gameDao.bestScore1();}
}
