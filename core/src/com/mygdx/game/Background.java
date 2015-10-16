package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.assets.AssetHelper;

/**
 * Created by Dani on 11/10/2015.
 */
public class Background {

    private Vector2 position;
    private Vector2 velocity;

    private int width, height;
    private Background auxBg;

    private float bgVelocity = -AssetHelper.getWidthPixels() * 200 / 1196;


    public Background(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(bgVelocity, 0);

    }

    public void update(float delta) {

        position.add(velocity.cpy().scl(delta));
        if (position.x <= -getWidth()) {
            position.x = auxBg.getX() + auxBg.getWidth() - 5;
        }
    }


    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }


    public Vector2 getVelocity() {
        return velocity;
    }

    public void onRestart(float x) {
        position.x = x;
    }

    public void asociateBg(Background bg) {
        auxBg = bg;
    }
}
