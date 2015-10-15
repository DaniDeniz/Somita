package com.mygdx.game.Model;

import com.mygdx.game.assets.AssetHelper;

/**
 * Created by Dani on 14/10/2015.
 */
public class MisilInvertor extends Misil {


    public MisilInvertor(float x, float y, int width, int height) {
        super(x, y, width, height);
        setVelocity(- (AssetHelper.getWidthPixels()*950/1196));
    }

    @Override
    public void onRestart(float x){
        position.x = x;
        position.y = ((float)(Math.random()*(max)))-10;
        velocity.x = - (AssetHelper.getWidthPixels()*950/1196);
        velocity.y = 0;
    }


}
