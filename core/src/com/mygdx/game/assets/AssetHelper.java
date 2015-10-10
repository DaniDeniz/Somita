package com.mygdx.game.assets;

import com.badlogic.gdx.Gdx;

/**
 * Created by USER on 02/10/2015.
 */
public class AssetHelper {

    private static float density;

    public AssetHelper(float density){
        this.density=density;
    }

    public static float getDensity() {
        return density;
    }
}
