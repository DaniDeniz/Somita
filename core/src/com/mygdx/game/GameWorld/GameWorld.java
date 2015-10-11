package com.mygdx.game.GameWorld;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Background;
import com.mygdx.game.CollisionManager.CollisionManager;
import com.mygdx.game.GameState.GameState;
import com.mygdx.game.GameWorld.GameWorldHelper.GameWorldUpdater;
import com.mygdx.game.GameWorld.GameWorldHelper.GameWorldRestarter;
import com.mygdx.game.Model.Helicopter;
import com.mygdx.game.Model.Misil;
import com.mygdx.game.Model.Vendaje;
import com.mygdx.game.Score.MisilUpdater;
import com.mygdx.game.Score.Score;
import com.mygdx.game.assets.AssetHelper;

/**
 * Created by USER on 28/09/2015.
 */
public class GameWorld {

    private Helicopter helicopter;
    private Misil[] misiles;
    private Vendaje[] vendajes;
    private Background[] bg;

    private Score score;
    private MisilUpdater misilUpdater;

    private int ALTO=Gdx.graphics.getHeight();
    private int ANCHO=Gdx.graphics.getWidth();

    private float proporcion = 0.7f;

    private GameState currentState;

    private CollisionManager collisionManager;
    private float explodeDelta, previousDelta;

    private GameWorldRestarter restarter;
    private GameWorldUpdater updater;


    public GameWorld(int midPointY) {
        this.helicopter = new Helicopter(33,ALTO/2,(int) ((450/ AssetHelper.getDensity())*proporcion), (int) ((213/ AssetHelper.getDensity())*proporcion));
        createMisiles(4);
        createVendajes(1);
        createBackground(2);
        score=new Score(this);
        explodeDelta=0;
        previousDelta=0;
        misilUpdater = new MisilUpdater(misiles,score);
        currentState = GameState.READY;
        collisionManager = new CollisionManager(this);
        restarter = new GameWorldRestarter(this);
        updater = new GameWorldUpdater(this);

    }

    private void createBackground(int n) {
        int mWitdh= ANCHO;
        int mHeight =ALTO;
        bg = new Background[n];

        for(int i = 0; i < bg.length;i++){
            bg[i]= new Background(ANCHO*i,0,mWitdh ,mHeight);
        }
        bg[0].asociateBg(bg[1]);
        bg[1].asociateBg(bg[0]);

    }

    public void update(float delta) {
        updater.update(delta,currentState);
    }


    public Vendaje[] getVendajes() {
        return vendajes;
    }

    private void createVendajes(int numVendajes) {
        int mWitdh=(int) ((164/ AssetHelper.getDensity())*proporcion);
        int mHeight =(int) ((112/AssetHelper.getDensity())*proporcion);
        vendajes = new Vendaje[numVendajes];

        for(int i = 0; i < vendajes.length;i++){
            vendajes[i]= new Vendaje(ANCHO+ANCHO*((i+0.f)/numVendajes),ALTO/((float)(Math.random()*(10-1))+1),mWitdh ,mHeight);
        }
    }

    public float getExplodeDelta() {
        return explodeDelta;
    }

    public void setExplodeDelta(float explodeDelta) {
        this.explodeDelta = explodeDelta;
    }

    public float getPreviousDelta() {
        return previousDelta;
    }

    public void setPreviousDelta(float previousDelta) {
        this.previousDelta = previousDelta;
    }

    public Background[] getBg() {
        return bg;
    }

    public MisilUpdater getMisilUpdater() {
        return misilUpdater;
    }

    public void setState(GameState gs){
        currentState = gs;
    }

    public GameState getCurrentState(){
        return currentState;
    }

    public Helicopter getHelicopter(){
        return helicopter;
    }

    public Misil[] getMisiles(){
        return misiles;
    }



    public CollisionManager getCollisionManager() {
        return collisionManager;
    }

    private void createMisiles(int numMisiles){
        int mWitdh=(int) ((314/ AssetHelper.getDensity())*proporcion);
        int mHeight =(int) ((80/AssetHelper.getDensity())*proporcion);
        misiles = new Misil[numMisiles];

        for(int i = 0; i < misiles.length;i++){

            misiles[i]= new Misil(ANCHO+ANCHO*((i+0.f)/numMisiles),ALTO/((float)(Math.random()*(10-1))+1),mWitdh ,mHeight);
        }

    }

    public Score getScore(){
        return score;
    }

    public void onRestart(){
        restarter.onRestart();
    }


}
