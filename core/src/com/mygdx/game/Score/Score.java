package com.mygdx.game.Score;

import com.mygdx.game.GameWorld.GameWorld;

/**
 * Created by USER on 05/10/2015.
 */
public class Score {

    private double scorePoints;
    private double scoreTime;
    private int incremment=2;
    private GameWorld myWorld;

    public Score(GameWorld myWorld) {
        scorePoints=0;
        scoreTime=0;
        this.myWorld=myWorld;
    }

    public void update(float delta){
        if(!myWorld.isCollided()){
            scoreTime+=incremment*delta;
        }
    }

    public double getValue(){
        return scorePoints+scoreTime;
    }

    public int getIncremment() {
        return incremment;
    }

    public void setIncremment(int incremment) {
        this.incremment = incremment;
    }

    public double getScoreTime(){
        return scoreTime;
    }

    public void vendajeCollided(){
        scorePoints+=10;
    }
}
