package com.mygdx.game.GameWorld.GameWorldHelper;

import com.mygdx.game.CollisionManager.CollisionManager;
import com.mygdx.game.GameState.GameState;
import com.mygdx.game.GameWorld.GameWorld;
import com.mygdx.game.Model.Helicopter;
import com.mygdx.game.Model.Misil;
import com.mygdx.game.Model.Vendaje;
import com.mygdx.game.Score.MisilUpdater;
import com.mygdx.game.Score.Score;
import com.mygdx.game.assets.AssetLoader;
import com.mygdx.game.assets.AudioPlayer;

/**
 * Created by Dani on 11/10/2015.
 */
public class GameWorldUpdater {
    private GameWorld myWorld;

    private Helicopter helicopter;
    private Misil[] misils;
    private Vendaje[] vendajes;

    private Score score;

    private CollisionManager collisionManager;
    private MisilUpdater misilUpdater;

    public GameWorldUpdater(GameWorld myWorld) {
        this.myWorld = myWorld;

        helicopter = myWorld.getHelicopter();
        misils = myWorld.getMisiles();
        vendajes = myWorld.getVendajes();

        score = myWorld.getScore();

        collisionManager = myWorld.getCollisionManager();
        misilUpdater = myWorld.getMisilUpdater();

    }

    public void update(float delta, GameState currentState) {
        switch (currentState){
            case READY: updateReady(delta);
                break;
            case RUNNING: updateRunning(delta);
                break;
            case GAMEOVER: updateGameOver(delta);
        }
    }

    private void updateRunning(float delta){
        helicopter.update(delta);
        misilUpdate(delta);
        vendajeUpdate(delta);
        collision();
        score.update(delta);
        misilUpdater.update();
        updateBackground(delta);
        myWorld.getAudioPlayer().play();
    }

    private void vendajeUpdate(float delta) {
        for(int i = 0; i < vendajes.length; i++){
            vendajes[i].update(delta);
        }
    }

    private void misilUpdate(float delta){
        for(int i = 0; i < misils.length; i++){
            misils[i].update(delta);
        }
    }

    private void updateBackground(float delta) {
        for(int i = 0; i < myWorld.getBg().length; i++){
            myWorld.getBg()[i].update(delta);
        }
    }

    private void updateReady(float delta){
        updateBackground(delta);
    }

    private void updateGameOver(float delta){
        updateGameOverMisils(delta);
        updateGameOverVendaje(delta);
        updateGameOverHighScore();

    }

    private void updateGameOverHighScore() {
        int highScore = AssetLoader.pref.getInteger("HighScore");
        if (highScore <= (int)(myWorld.getScore().getValue())){
            myWorld.getAudioPlayer().playSoma();
        } else {
            myWorld.getAudioPlayer().playEzequie();
        }
    }

    private void updateGameOverVendaje(float delta) {
        for(int i = 0; i < vendajes.length; i++){
            vendajes[i].updateGameOver(delta);
        }
    }

    private void updateGameOverMisils(float delta) {
        for(int i = 0; i < misils.length; i++){
            misils[i].updateGameOver(delta);
        }
    }

    private void collision(){
        collisionManager.isCollisionMisil();
        if(collisionManager.isCollisionVendaje()) {
            score.vendajeCollided();
        }
    }
}
