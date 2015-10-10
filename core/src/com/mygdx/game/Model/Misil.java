package com.mygdx.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by USER on 29/09/2015.
 */
public class Misil {
    private Vector2 position;
    private Vector2 velocity;
    private float max= Gdx.graphics.getHeight()-getHeight()-20;

    private int width,height;
    private Rectangle bounds;
    private int maxVelocity;


    public Misil(float x, float y, int width, int height) {
        this.width=width;
        this.height=height;
        position = new Vector2(x,y);
        velocity = new Vector2(-500,0);
        bounds= new Rectangle(x,y,width,height);
        maxVelocity=-1000;

    }

    public void update(float delta){

        position.add(velocity.cpy().scl(delta));

        if(position.x <= -getWidth()){
            position.x= Gdx.graphics.getWidth();
            position.y = ((float)(Math.random()*(max)))-10;
        }
        bounds.setPosition(position);
    }

    public void updateGameOver(float delta){

        position.add(velocity.cpy().scl(delta));

        if(position.x <= -getWidth()){
            position.x= -getWidth()*2;
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

    public void onRestart(float x){
        position.x = x;
        position.y = ((float)(Math.random()*(max)))-10;
        velocity.x = -500;
        velocity.y = 0;
    }
}
