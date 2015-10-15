package com.mygdx.game.GameWorld.GameWorldHelper;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameWorld.GameWorld;
import com.mygdx.game.Model.Helicopter;
import com.mygdx.game.Model.Misil;
import com.mygdx.game.Model.MisilInvertor;
import com.mygdx.game.Model.Vendaje;

/**
 * Created by Dani on 11/10/2015.
 */
public class GameWorldRestarter {

    private GameWorld myWorld;

    private Helicopter helicopter;
    private Misil[] misils;
    private Vendaje[] vendajes;
    private MisilInvertor[] invertors;

    private int ANCHO = Gdx.graphics.getWidth();

    public GameWorldRestarter(GameWorld myWorld) {
        this.myWorld = myWorld;
        this.helicopter = myWorld.getHelicopter();
        this.misils = myWorld.getMisiles();
        this.vendajes = myWorld.getVendajes();
        invertors = myWorld.getInvertors();

    }

    public void onRestart(){
        helicopter.onRestart();
        onRestartMisils();
        onRestartInvertors();
        myWorld.getScore().onRestart();
        onRestartVendajes();
        onRestartDeltaAnimation();
        onRestartBackground();
        myWorld.getAudioPlayer().onRestart();
    }

    private void onRestartMisils(){
        for(int i = 0; i < misils.length;i++){
            misils[i].onRestart(ANCHO+ANCHO*((i+0.f)/misils.length));
        }
    }

    private void onRestartInvertors(){
        for(int i = 0; i < invertors.length;i++){
            invertors[i].onRestart(ANCHO+ANCHO*((i+0.f)/invertors.length));
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
