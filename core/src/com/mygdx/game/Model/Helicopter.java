package com.mygdx.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by USER on 28/09/2015.
 */
public class Helicopter {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private int width,height;
    private Rectangle bounds;

    public Helicopter(float x, float y, int width, int height) {
        this.width=width;
        this.height=height;
        position = new Vector2(x,y);
        velocity = new Vector2(0,0);
        acceleration = new Vector2(0,-460);
        bounds = new Rectangle(x,y,width,height*100/213);


    }

    public void update(float delta){
        velocity.add(acceleration.cpy().scl(delta));
        if(velocity.y < -460){
            velocity.y =-300;
        }

        position.add(velocity.cpy().scl(delta));

        if(position.y > Gdx.graphics.getHeight()-getHeight()){
            position.y=Gdx.graphics.getHeight()-getHeight();
        }
        if(position.y <0){
            position.y=0;
        }
        bounds.setPosition(position);
    }

    public void onClick() {
        velocity.y = 270;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public boolean isGoingUp(){
        return velocity.y > 0;
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




}
