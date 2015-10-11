package com.mygdx.game.GameWorld.GameWorldHelper;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameWorld.GameWorld;
import com.mygdx.game.Model.Helicopter;
import com.mygdx.game.Model.Misil;
import com.mygdx.game.Model.Vendaje;

/**
 * Created by Dani on 11/10/2015.
 */
public class GameWorldRestarter {

    private GameWorld myWorld;

    private Helicopter helicopter;
    private Misil[] misils;
    private Vendaje[] vendajes;

    private int ANCHO = Gdx.graphics.getWidth();

    public GameWorldRestarter(GameWorld myWorld) {
        this.myWorld = myWorld;
        this.helicopter = myWorld.getHelicopter();
        this.misils = myWorld.getMisiles();
        this.vendajes = myWorld.getVendajes();

    }

    public void onRestart(){
        helicopter.onRestart();
        onRestartMisils();
        myWorld.getScore().onRestart();
        onRestartVendajes();
        onRestartDeltaAnimation();
        onRestartBackground();
    }

    private void onRestartMisils(){
        for(int i = 0; i < misils.length;i++){
            misils[i].onRestart(ANCHO+ANCHO*((i+0.f)/misils.length));
        }
    }

    private void onRestartVendajes(){
        for(int i = 0; i < vendajes.length;i++){
            vendajes[i].onRestart(ANCHO+ANCHO*((i+0.f)/vendajes.length));
        }
    }

    private void onRestartBackground(){
        for(int i = 0; i < myWorld.getBg().length;i++){
            myWorld.getBg()[i].onRestart(ANCHO*i);
        }
    }

    private void onRestartDeltaAnimation(){
        myWorld.setExplodeDelta(0);
        myWorld.setPreviousDelta(0);
    }
}
