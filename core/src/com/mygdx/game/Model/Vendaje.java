package com.mygdx.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Dani on 07/10/2015.
 */
public class Vendaje {
    private com.badlogic.gdx.math.Vector2 position;
    private com.badlogic.gdx.math.Vector2 velocity;
    private float max= Gdx.graphics.getHeight()-getHeight()-20;

    private int width,height;
    private Rectangle bounds;


    public Vendaje(float x, float y, int width, int height) {
        this.width=width;
        this.height=height;
        position = new com.badlogic.gdx.math.Vector2(x,y);
        velocity = new com.badlogic.gdx.math.Vector2(-300,0);
        bounds= new Rectangle(x,y,width,height);

    }

    public void update(float delta){

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

    public void hasCollided(){
        position.x= Gdx.graphics.getWidth();
        position.y = ((float)(Math.random()*(max)))-10;
    }


}


