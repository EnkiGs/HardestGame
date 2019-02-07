package iut.projet.hardestgame.controllers;

import java.util.ArrayList;

import iut.projet.hardestgame.models.Player;
import iut.projet.hardestgame.models.Score;

public class ScoreControler {

    private Player name;
    private ArrayList<Score> listScore;

    public ScoreControler(Player name, ArrayList<Score> listScore) {
        this.name = name;
        this.listScore = listScore;
    }

    public Player getName() {
        return name;
    }

    public void setName(Player name) {
        this.name = name;
    }

    public ArrayList<Score> getListScore() {
        return listScore;
    }

    public void setListScore(ArrayList<Score> listScore) {
        this.listScore = listScore;
    }
}
