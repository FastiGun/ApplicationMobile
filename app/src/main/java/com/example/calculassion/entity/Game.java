package com.example.calculassion.entity;

public class Game extends BaseEntity {
    String pseudo;
    String score;
    String tentatives;

    public String getPseudo() {
        return pseudo;
    }

    public String getScore() {
        return score;
    }

    public String getTentatives() {
        return tentatives;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setTentatives(String tentatives) {
        this.tentatives = tentatives;
    }
}
