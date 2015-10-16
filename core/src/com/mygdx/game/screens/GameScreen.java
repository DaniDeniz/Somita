package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameWorld.GameRenderer;
import com.mygdx.game.GameWorld.GameWorld;
import com.mygdx.game.InputHandler.InputHandler;

/**
 * Created by USER on 28/09/2015.
 */
public class GameScreen implements Screen{

    private GameWorld world;
    private GameRenderer renderer;
    private float runTime;

    public GameScreen() {
        Gdx.app.log("GameScreen","Attached");
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        int midPointY = (int) (gameHeight / 2);

        world = new GameWorld(midPointY);
        renderer = new GameRenderer(world,(int)gameHeight,midPointY);

        Gdx.input.setInputProcessor(new InputHandler(world));

    }

    @Override
    public void show() {

    }



    @Override
    public void render(float delta) {
        runTime+=delta;
        world.update(delta);
        renderer.render(runTime,delta);
        //Gdx.gl.glClearColor(10 / 255.0f, 15 / 255.0f, 230 / 255.0f, 1);
       // Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
