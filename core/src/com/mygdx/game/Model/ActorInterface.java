package com.mygdx.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.*;

/**
 * Created by Dani on 10/10/2015.
 */
public interface ActorInterface {



    public abstract void update(float delta);

    public abstract void onClick();

    public abstract float getX();

    public abstract float getY();

    public abstract float getWidth();

    public abstract float getHeight();

    public abstract void onRestart();

    public abstract Rectangle getBounds();



}
