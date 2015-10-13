package com.mygdx.game.Score;

import com.mygdx.game.Model.Misil;
import com.mygdx.game.assets.AssetHelper;
import com.mygdx.game.assets.AssetLoader;

/**
 * Created by Dani on 06/10/2015.
 */
public class MisilUpdater {

    private Misil[] misils;
    private Score score;
    private float acceleration = AssetHelper.getWidthPixels()*50/1196;
    private boolean isIncrementing;

    public MisilUpdater(Misil[] misils,Score score) {
        this.misils = misils;
        this.score=score;
        isIncrementing=false;
    }

    public void update(){
        int actualScore = (int) score.getScoreTime();
        if(!isIncrementing && actualScore%25==0 && actualScore!=0){
            if(misils[0].getMaxVelocity() < misils[0].getVelocity().x){
                misilsIncrement();
                score.setIncremment(score.getIncremment()+1);
                isIncrementing=true;
            }
        }

        if(actualScore%25!=0){
            isIncrementing=false;
        }

    }

    private void misilsIncrement() {
        for(int i = 0; i < misils.length; i++){
            misils[i].incremment(acceleration,0);
        }
    }

    public void onRestart(){
        isIncrementing = false;
    }
}
