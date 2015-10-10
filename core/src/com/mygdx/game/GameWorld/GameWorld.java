package com.mygdx.game.GameWorld;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.CollisionManager.CollisionManager;
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
    private boolean areCollided;
    private Score score;
    private MisilUpdater misilUpdater;
    private int ALTO=Gdx.graphics.getHeight();
    private int ANCHO=Gdx.graphics.getWidth();

    private float proporcion = 0.7f;

    

    public GameWorld(int midPointY) {
        this.helicopter = new Helicopter(33,ALTO/2,(int) ((450/ AssetHelper.getDensity())*proporcion), (int) ((213/ AssetHelper.getDensity())*proporcion));
        createMisiles(4);
        createVendajes(1);
        areCollided=false;
        score=new Score(this);
        misilUpdater = new MisilUpdater(misiles,score);
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

    public void update(float delta) {
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
        if(!areCollided){
            areCollided = CollisionManager.isCollisionMisil(helicopter, misiles);
            if(CollisionManager.isCollisionVendaje(helicopter,vendajes)){
                score.vendajeCollided();
            }
        }

    }

    public boolean isCollided() {
        return areCollided;
    }

    public Score getScore(){
        return score;
    }
}
