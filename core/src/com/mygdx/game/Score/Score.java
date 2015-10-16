package com.mygdx.game.Score;

import com.mygdx.game.GameWorld.GameWorld;

/**
 * Created by USER on 05/10/2015.
 */
public class Score {

    private double scorePoints;
    private double scoreTime;
    private int incremment = 5;

    public Score(GameWorld myWorld) {
        scorePoints = 0;
        scoreTime = 0;
    }

    public void update(float delta) {
        scoreTime += incremment * delta;
    }

    public int getValue() {
        return (int) (scoreTime + scorePoints);
    }

    public int getIncremment() {
        return incremment;
    }

    public void setIncremment(int incremment) {
        this.incremment = incremment;
    }

    public double getScoreTime() {
        return scoreTime;
    }

    public void vendajeCollided() {
        scorePoints += 15;
    }

    public void onRestart() {
        scorePoints = 0;
        scoreTime = 0;
        incremment = 5;
    }
}
