package com.mygdx.game.GameWorld;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.CollisionManager.CollisionManager;
import com.mygdx.game.GameState.GameState;
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

    private Score score;
    private MisilUpdater misilUpdater;

    private int ALTO=Gdx.graphics.getHeight();
    private int ANCHO=Gdx.graphics.getWidth();

    private float proporcion = 0.7f;

    private GameState currentState;

    private CollisionManager collisionManager;
    private float explodeDelta, previousDelta;


    public GameWorld(int midPointY) {
        this.helicopter = new Helicopter(33,ALTO/2,(int) ((450/ AssetHelper.getDensity())*proporcion), (int) ((213/ AssetHelper.getDensity())*proporcion));
        createMisiles(4);
        createVendajes(1);
        score=new Score(this);
        explodeDelta=0;
        previousDelta=0;
        misilUpdater = new MisilUpdater(misiles,score);
        currentState = GameState.READY;
        collisionManager = new CollisionManager(this);
    }

    public void update(float delta) {
        switch (currentState){
            case READY: updateReady(delta);
                break;
            case RUNNING: updateRunning(delta);
                break;
            case GAMEOVER: updateGameOver(delta);
        }
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



    private void updateReady(float delta){

    }

    private void updateGameOver(float delta){
        updateGameOverMisils(delta);
        updateGameOverVendaje(delta);

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

    private void updateGameOverVendaje(float delta) {
        for(int i = 0; i < vendajes.length; i++){
            vendajes[i].updateGameOver(delta);
        }
    }

    private void updateGameOverMisils(float delta) {
        for(int i = 0; i < misiles.length; i++){
            misiles[i].updateGameOver(delta);
        }
    }

    private void updateRunning(float delta){
        helicopter.update(delta);
        misilUpdate(delta);
        vendajeUpdate(delta);
        collision();
        score.update(delta);
        misilUpdater.update();
    }

    private void vendajeUpdate(float delta) {
        for(int i = 0; i < vendajes.length; i++){
            vendajes[i].update(delta);
        }
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

    private void misilUpdate(float delta){
        for(int i = 0; i < misiles.length; i++){
            misiles[i].update(delta);
        }
    }

    private void createMisiles(int numMisiles){
        int mWitdh=(int) ((314/ AssetHelper.getDensity())*proporcion);
        int mHeight =(int) ((80/AssetHelper.getDensity())*proporcion);
        misiles = new Misil[numMisiles];

        for(int i = 0; i < misiles.length;i++){
            misiles[i]= new Misil(ANCHO+ANCHO*((i+0.f)/numMisiles),ALTO/((float)(Math.random()*(10-1))+1),mWitdh ,mHeight);
        }

    }

    private void collision(){
        collisionManager.isCollisionMisil();
        if(collisionManager.isCollisionVendaje()) {
            score.vendajeCollided();
        }

    }


    public Score getScore(){
        return score;
    }

    public void onRestart(){
        helicopter.onRestart();
        onRestartMisils();
        score.onRestart();
        onRestartVendajes();
        onRestartDeltaAnimation();

    }

    private void onRestartMisils(){
        for(int i = 0; i < misiles.length;i++){
            misiles[i].onRestart(ANCHO+ANCHO*((i+0.f)/misiles.length));
        }
    }

    private void onRestartVendajes(){
        for(int i = 0; i < vendajes.length;i++){
            vendajes[i].onRestart(ANCHO+ANCHO*((i+0.f)/vendajes.length));
        }
    }

    private void onRestartDeltaAnimation(){
        explodeDelta=0;
        previousDelta=0;
    }
}
