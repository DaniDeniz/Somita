package com.mygdx.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.assets.AssetHelper;

/**
 * Created by USER on 28/09/2015.
 */
public class Helicopter implements ActorInterface {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private int width, height;
    private Rectangle bounds;

    private float ac = -AssetHelper.getHeightPixels() * 460 / 720;
    private float increm = AssetHelper.getHeightPixels() * 320 / 720;

    private boolean hasBeenInverted;




    public Helicopter(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, ac);
        bounds = new Rectangle(x, y, width, height * 100 / 213);
        hasBeenInverted=false;

    }

    public void update(float delta) {
        velocity.add(acceleration.cpy().scl(delta));
        if (velocity.y < ac) {
            velocity.y = -AssetHelper.getHeightPixels() * 300 / 720;
            ;
        }

        position.add(velocity.cpy().scl(delta));

        if (position.y > Gdx.graphics.getHeight() - getHeight()) {
            position.y = Gdx.graphics.getHeight() - getHeight();
            velocity.y = velocity.y * 0.8f;
        }
        if (position.y < -getHeight() * 0.1f) {
            position.y = -getHeight() * 0.1f;
        }
        bounds.setPosition(position);

    }

    public void onClick() {
        velocity.y = increm;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public boolean isGoingUp() {
        return velocity.y > 0;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getAcceleration() {
        return acceleration.y;
    }

    public float getIncrem() {
        return increm;
    }

    public void setIncrem(float increm) {
        this.increm = increm;
    }

    public void setAcceleration(float y) {
        acceleration.y = y;
    }

    @Override
    public void onRestart() {
        position.x = 33;
        position.y = Gdx.graphics.getHeight() / 2;
        velocity.y = 0;
        acceleration.y = ac;
        increm = AssetHelper.getHeightPixels() * 320 / 720;
    }


    public Rectangle getBounds() {
        return bounds;
    }

    public boolean isHasBeenInverted() {
        return hasBeenInverted;
    }

    public void setHasBeenInverted(boolean hasBeenInverted) {
        this.hasBeenInverted = hasBeenInverted;
    }


}
