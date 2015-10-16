package com.mygdx.game.GameWorld;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Background;
import com.mygdx.game.CollisionManager.CollisionManager;
import com.mygdx.game.GameState.GameState;
import com.mygdx.game.GameWorld.GameWorldHelper.GameWorldUpdater;
import com.mygdx.game.GameWorld.GameWorldHelper.GameWorldRestarter;
import com.mygdx.game.Model.Helicopter;
import com.mygdx.game.Model.Misil;
import com.mygdx.game.Model.MisilInvertor;
import com.mygdx.game.Model.Vendaje;
import com.mygdx.game.Score.MisilUpdater;
import com.mygdx.game.Score.Score;
import com.mygdx.game.assets.AssetHelper;
import com.mygdx.game.assets.AudioPlayer;

/**
 * Created by USER on 28/09/2015.
 */
public class GameWorld {

    private Helicopter helicopter;
    private Misil[] misiles;
    private Vendaje[] vendajes;
    private MisilInvertor[] invertors;
    private Background[] bg;

    private Score score;
    private MisilUpdater misilUpdater;

    private int ALTO=Gdx.graphics.getHeight();
    private int ANCHO=Gdx.graphics.getWidth();

    private float proporcion = 0.7f;

    private GameState currentState;

    private CollisionManager collisionManager;
    private AudioPlayer audioPlayer;
    private float explodeDelta, previousDelta;

    private GameWorldRestarter restarter;
    private GameWorldUpdater updater;



    public GameWorld(int midPointY) {
        createHelicopter();
        createMisiles(4);
        createMisilesInvertor(1);
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
        audioPlayer = new AudioPlayer(this);

    }

    private void createHelicopter() {
        int width = (int) (((AssetHelper.getWidthPixels()*225)/1196)*proporcion);
        int height = (int) (((AssetHelper.getHeightPixels()*106)/720)*proporcion);
        this.helicopter = new Helicopter(33,ALTO/2,width,height);
        //pantalla: 1196*720
        //helicoptero: 225x106
        //misil: 157x40
        //vendaje: 82x56
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
        updater.update(delta, currentState);
    }


    public Vendaje[] getVendajes() {
        return vendajes;
    }

    private void createVendajes(int numVendajes) {
        int mWitdh= (int) ((AssetHelper.getWidthPixels()*82/1196)*proporcion);
        int mHeight = (int) ((AssetHelper.getHeightPixels()*56/720)*proporcion);
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

    public MisilInvertor[] getInvertors() {
        return invertors;
    }

    private void createMisiles(int numMisiles){
        int mWitdh= (int) ((AssetHelper.getWidthPixels()*157/1196)*proporcion);
        int mHeight = (int) ((AssetHelper.getHeightPixels()*40/720)*proporcion);

        misiles = new Misil[numMisiles];

        for(int i = 0; i < misiles.length;i++){

            misiles[i]= new Misil(ANCHO+ANCHO*((i+0.f)/numMisiles),ALTO/((float)(Math.random()*(10-1))+1),mWitdh ,mHeight);
        }

    }

    private void createMisilesInvertor(int numMisiles){
        int mWitdh= (int) ((AssetHelper.getWidthPixels()*157/1196)*proporcion);
        int mHeight = (int) ((AssetHelper.getHeightPixels()*40/720)*proporcion);
        invertors = new MisilInvertor[numMisiles];

        for(int i = 0; i < invertors.length;i++){

            invertors[i]= new MisilInvertor(ANCHO+ANCHO*((i+0.f)/numMisiles),ALTO/((float)(Math.random()*(10-1))+1),mWitdh ,mHeight);
        }

    }

    public Score getScore(){
        return score;
    }

    public void onRestart(){
        restarter.onRestart();
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }


}
