package com.mygdx.game.InputHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.GameState.GameState;
import com.mygdx.game.GameWorld.GameWorld;
import com.mygdx.game.Model.Helicopter;

/**
 * Created by USER on 28/09/2015.
 */
public class InputHandler implements InputProcessor {

    private GameWorld myWorld;

    public InputHandler(GameWorld myWorld) {
        this.myWorld = myWorld;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (myWorld.getCurrentState() == GameState.RUNNING) {
            myWorld.getHelicopter().onClick();
        } else if (myWorld.getCurrentState() == GameState.READY) {
            myWorld.setState(GameState.RUNNING);
        } else if (myWorld.getCurrentState() == GameState.GAMEOVER) {
            myWorld.setState(GameState.READY);
            myWorld.onRestart();
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;

    }

}
