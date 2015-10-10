package com.mygdx.game.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Model.Helicopter;
import com.mygdx.game.Model.Misil;
import com.mygdx.game.Model.Vendaje;
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
    private float explodeDelta,previousRuntime;

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
        explodeDelta=0;
        previousRuntime=0;
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
        batcher.draw(AssetLoader.bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

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

    private void renderMisiles(SpriteBatch batcher) {
        Misil[] misiles = myWorld.getMisiles();
        for(int i =0; i < misiles.length; i++){
            batcher.draw(AssetLoader.misil, misiles[i].getX(), misiles[i].getY(),misiles[i].getWidth(),misiles[i].getHeight());
        }
    }

    private void renderHelicopter(SpriteBatch batcher,float runTime){
        Helicopter helicopter= myWorld.getHelicopter();

        if(myWorld.isCollided()){
            TextureRegion animation = AssetLoader.helicopterExplosion.getKeyFrame(explodeDelta,false);
            batcher.draw(animation, helicopter.getX(), helicopter.getY()-helicopter.getHeight(), helicopter.getWidth()*3,helicopter.getHeight()*3);
            if(previousRuntime != 0) {
                explodeDelta += runTime - previousRuntime;
            }
            previousRuntime=runTime;

        } else {
            if(helicopter.isGoingUp()){
                TextureRegion animation = AssetLoader.helicopterMove.getKeyFrame(runTime,true);
                batcher.draw(animation, helicopter.getX(), helicopter.getY(), helicopter.getWidth(),helicopter.getHeight());
            } else {
                batcher.draw(AssetLoader.helicopterStopped, helicopter.getX(), helicopter.getY(), helicopter.getWidth(),helicopter.getHeight());
            }
        }
    }

    private void renderScore(SpriteBatch batcher){
        int score = (int) myWorld.getScore().getValue();

        font.draw(batcher,"Puntuación: "+score,ANCHO/2,ALTO-50);
    }

    private void renderVendaje(SpriteBatch batcher){
        Vendaje[] vendajes = myWorld.getVendajes();
        for(int i =0; i < vendajes.length; i++){
            batcher.draw(AssetLoader.vendaje, vendajes[i].getX(), vendajes[i].getY(),vendajes[i].getWidth(),vendajes[i].getHeight());
        }
    }


}
