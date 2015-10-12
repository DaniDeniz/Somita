package com.mygdx.game.CollisionManager;

import com.mygdx.game.GameState.GameState;
import com.mygdx.game.GameWorld.GameWorld;
import com.mygdx.game.Model.Helicopter;
import com.mygdx.game.Model.Misil;
import com.mygdx.game.Model.Vendaje;

/**
 * Created by USER on 05/10/2015.
 */
public class CollisionManager {

    private GameWorld myWorld;

    private Helicopter helicopter;
    private Misil[] misils;
    private Vendaje[] vendajes;

    public CollisionManager(GameWorld myWorld) {
        this.myWorld = myWorld;
        helicopter = myWorld.getHelicopter();
        misils = myWorld.getMisiles();
        vendajes = myWorld.getVendajes();
    }

    public boolean isCollisionMisil(){
        for (int i = 0; i < misils.length; i++) {
            if(helicopter.getBounds().overlaps(misils[i].getBounds())) {
                myWorld.setState(GameState.GAMEOVER);
                HighScoreManager.highScore((int) myWorld.getScore().getValue());
                return true;
            }
        }
        return false;
    }

    public boolean isCollisionVendaje(){
        for (int i = 0; i < vendajes.length; i++) {
            if(helicopter.getBounds().overlaps(vendajes[i].getBounds())) {
                vendajes[i].hasCollided();
                return true;
            }
        }
        return false;

    }

}
