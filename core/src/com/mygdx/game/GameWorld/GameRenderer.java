package com.mygdx.game.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Background;
import com.mygdx.game.GameState.GameState;
import com.mygdx.game.Model.Helicopter;
import com.mygdx.game.Model.Misil;
import com.mygdx.game.Model.Vendaje;
import com.mygdx.game.assets.AssetHelper;
import com.mygdx.game.assets.AssetLoader;

/**
 * Created by USER on 28/09/2015.
 */
public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batcher;
    private BitmapFont font;
    private int ALTO, ANCHO;

    private int midPointY,gameHeight;


    public GameRenderer(GameWorld world, int midPointY, int gameHeight) {
        myWorld=world;
        this.midPointY= midPointY;
        this.gameHeight= gameHeight;

        cam = new OrthographicCamera();
        ALTO = Gdx.graphics.getHeight();
        ANCHO = Gdx.graphics.getWidth();
        cam.setToOrtho(false, ANCHO, ALTO);
        batcher = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        font = new BitmapFont();
        font.setColor(1, 0, 0, 1);
    }

    public void render(float runTime) {
        Gdx.app.log("GameRenderer", "render");

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();
        batcher.setProjectionMatrix(cam.combined);
        // Disable transparency
        // This is good for performance when drawing images that do not require
        // transparency.
        batcher.disableBlending();
        renderBackground(batcher);
        //batcher.draw(AssetLoader.bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // The bird needs transparency, so we enable that again.
        batcher.enableBlending();

        // Draw bird at its coordinates. Retrieve the Animation object from
        // AssetLoader
        // Pass in the runTime variable to get the current frame.
        renderVendaje(batcher);
        renderMisiles(batcher);
        renderHelicopter(batcher, runTime);


        renderScore(batcher);

        // End SpriteBatch
        batcher.end();

    }

    private void renderBackground(SpriteBatch batcher) {
        Background[] bg = myWorld.getBg();
        for(int i =0; i < bg.length; i++) {
            if (i == 0) {
                batcher.draw(AssetLoader.bgIzq, bg[i].getX(), bg[i].getY(), bg[i].getWidth(), bg[i].getHeight());
            } else {
                batcher.draw(AssetLoader.bgDer, bg[i].getX(), bg[i].getY(), bg[i].getWidth(), bg[i].getHeight());
            }

        }
    }

    private void renderMisiles(SpriteBatch batcher) {
        Misil[] misiles = myWorld.getMisiles();
        for(int i =0; i < misiles.length; i++){
            batcher.draw(AssetLoader.misil, misiles[i].getX(), misiles[i].getY(),misiles[i].getWidth(),misiles[i].getHeight());
        }
    }

    private void renderHelicopter(SpriteBatch batcher,float runTime){
        Helicopter helicopter= myWorld.getHelicopter();

        if(myWorld.getCurrentState()==GameState.GAMEOVER){
            TextureRegion animation = AssetLoader.helicopterExplosion.getKeyFrame(myWorld.getExplodeDelta(),false);
            batcher.draw(animation, helicopter.getX(), helicopter.getY()-helicopter.getHeight(), helicopter.getWidth()*3,helicopter.getHeight()*3);
            if(myWorld.getPreviousDelta() != 0) {
                myWorld.setExplodeDelta(myWorld.getExplodeDelta() + runTime - myWorld.getPreviousDelta());
            }
            myWorld.setPreviousDelta(runTime);

        } else {
            if(helicopter.isGoingUp() || myWorld.getCurrentState()== GameState.READY){
                TextureRegion animation = AssetLoader.helicopterMove.getKeyFrame(runTime,true);
                batcher.draw(animation, helicopter.getX(), helicopter.getY(), helicopter.getWidth(),helicopter.getHeight());
            } else {
                batcher.draw(AssetLoader.helicopterStopped, helicopter.getX(), helicopter.getY(), helicopter.getWidth(),helicopter.getHeight());
            }
        }
    }

    private void renderScore(SpriteBatch batcher){
        int score = (int) myWorld.getScore().getValue();
        String puntuacion = "Score: "+score;
        AssetLoader.shadow.draw(batcher, puntuacion, ANCHO / 2 - puntuacion.length()*font.getScaleX()+1, ALTO - 50-1);
        AssetLoader.font.draw(batcher, puntuacion, ANCHO / 2 - puntuacion.length()*font.getScaleX(), ALTO - 50);
        if(GameState.GAMEOVER == myWorld.getCurrentState()){
            String highScore = "HighScore: "+AssetLoader.pref.getInteger("HighScore");
            AssetLoader.shadow.draw(batcher, highScore, ANCHO / 2 - 3*highScore.length()*font.getScaleX()+1, ALTO/2);
            AssetLoader.font.draw(batcher, highScore, ANCHO / 2 -3* highScore.length()*font.getScaleX(), ALTO /2);
        }

    }

    private void renderVendaje(SpriteBatch batcher){
        Vendaje[] vendajes = myWorld.getVendajes();
        for(int i =0; i < vendajes.length; i++){
            batcher.draw(AssetLoader.vendaje, vendajes[i].getX(), vendajes[i].getY(),vendajes[i].getWidth(),vendajes[i].getHeight());
        }
    }


}
