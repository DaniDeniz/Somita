package com.mygdx.game.Score;

import com.mygdx.game.Model.Misil;

/**
 * Created by Dani on 06/10/2015.
 */
public class MisilUpdater {

    private Misil[] misils;
    private Score score;
    private int auxIncrement;

    public MisilUpdater(Misil[] misils,Score score) {
        this.misils = misils;
        this.score=score;
        auxIncrement=0;
    }

    public void update(){
        int actualScore = (int) score.getScoreTime();
        if(actualScore-auxIncrement >= 25){
            auxIncrement=actualScore;
            misilsIncrement();
            if(misils[0].getMaxVelocity() < misils[0].getVelocity().x)
                score.setIncremment(score.getIncremment()+2);
        }

    }

    private void misilsIncrement() {
        for(int i = 0; i < misils.length; i++){
            misils[i].incremment(50,0);
        }
    }
}
