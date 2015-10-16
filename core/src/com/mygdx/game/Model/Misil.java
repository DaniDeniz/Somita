package com.mygdx.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.assets.AssetHelper;

/**
 * Created by USER on 29/09/2015.
 */
public class Misil {
    protected Vector2 position;
    protected Vector2 velocity;
    protected float max = Gdx.graphics.getHeight() - getHeight() - 20;

    protected int width, height;
    protected Rectangle bounds;
    protected int maxVelocity = -(AssetHelper.getWidthPixels() * 900 / 1196);
    protected float inicialVelocity = -(AssetHelper.getWidthPixels() * 600 / 1196);


    public Misil(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(inicialVelocity, 0);
        bounds = new Rectangle(x, y, width, height);

    }

    public void update(float delta) {

        position.add(velocity.cpy().scl(delta));

        if (position.x <= -getWidth()) {
            position.x = Gdx.graphics.getWidth();
            position.y = ((float) (Math.random() * (max))) - 10;
        }
        bounds.setPosition(position);
    }

    public void updateGameOver(float delta) {

        position.add(velocity.cpy().scl(delta));

        if (position.x <= -getWidth()) {
            position.x = -getWidth() * 2;
        }
        bounds.setPosition(position);
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

    public Rectangle getBounds() {
        return bounds;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(float x) {
        velocity.x = x;
    }

    public int getMaxVelocity() {

        return maxVelocity;
    }

    public void incremment(float x, float y) {
        if (velocity.x > maxVelocity) {
            velocity.add(-x, y);
        }
    }

    public void hasCollided() {
        position.x = Gdx.graphics.getWidth();
        position.y = ((float) (Math.random() * (max))) - 10;
    }

    public void onRestart(float x) {
        position.x = x;
        position.y = ((float) (Math.random() * (max))) - 10;
        velocity.x = inicialVelocity;
        velocity.y = 0;
    }
}
