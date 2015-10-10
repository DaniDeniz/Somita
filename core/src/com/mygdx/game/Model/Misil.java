package com.mygdx.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by USER on 29/09/2015.
 */
public class Misil {
    private com.badlogic.gdx.math.Vector2 position;
    private com.badlogic.gdx.math.Vector2 velocity;
    private com.badlogic.gdx.math.Vector2 acceleration;
    private float max= Gdx.graphics.getHeight()-getHeight()-20;

    private int width,height;
    private Rectangle bounds;
    private int maxVelocity;


    public Misil(float x, float y, int width, int height) {
        this.width=width;
        this.height=height;
        position = new com.badlogic.gdx.math.Vector2(x,y);
        velocity = new com.badlogic.gdx.math.Vector2(-400,0);
        acceleration = new com.badlogic.gdx.math.Vector2(-460,-20);

        bounds= new Rectangle(x,y,width,height);
        maxVelocity=-1000;

    }

    public void update(float delta){
        /*velocity.add(acceleration.cpy().scl(delta));
        if(velocity.x < -300){
            velocity.x =-300;
        }*/

        position.add(velocity.cpy().scl(delta));

        if(position.x <= -getWidth()){
            position.x= Gdx.graphics.getWidth();
            position.y = ((float)(Math.random()*(max)))-10;
        }
        bounds.setPosition(position);
    }

    public void onClick() {

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

    public Rectangle getBounds(){
        return bounds;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public int getMaxVelocity() {

        return maxVelocity;
    }

    public void incremment(float x, float y){
        if(velocity.x > maxVelocity){
            velocity.add(-x,y);
        }

    }
}
