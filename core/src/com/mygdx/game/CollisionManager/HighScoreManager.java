package com.mygdx.game.CollisionManager;

import com.mygdx.game.assets.AssetLoader;

/**
 * Created by Dani on 12/10/2015.
 */
public class HighScoreManager {
    public static boolean highScore(int score) {
        int highScore = AssetLoader.pref.getInteger("HighScore");
        if (score > highScore) {
            AssetLoader.pref.putInteger("HighScore", score);
            AssetLoader.pref.flush();
            return true;
        }
        return false;
    }
}
