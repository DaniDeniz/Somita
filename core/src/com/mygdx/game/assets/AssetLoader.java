package com.mygdx.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;



/**
 * Created by USER on 28/09/2015.
 */
public class AssetLoader {
    public static Texture bg,explosion;
    public static Texture helicopter,misil,vendaje;
    public static TextureRegion helicopterStopped;
    public static Animation helicopterMove,helicopterExplosion;




    public static void load(){
        bg = new Texture("bg.png");
        helicopter = new Texture("helicoptero.png");
        misil = new Texture("misilNuclear.png");
        explosion= new Texture("explosion.png");
        vendaje= new Texture("vendaje.png");

        helicopterStopped = new TextureRegion(helicopter,450,213);

        TextureRegion[][] regions = TextureRegion.split(helicopter, 450, 213);
        TextureRegion[] helicopterMoving = regions[0];
        helicopterMove = new Animation(0.1f,helicopterMoving);

        TextureRegion[][] regionsE = TextureRegion.split(explosion, 190, 150);
        TextureRegion[] helicopterExploding = regionsE[0];
        helicopterExplosion = new Animation(0.15f,helicopterExploding);


    }

    public static void dispose(){
        bg.dispose();
        helicopter.dispose();
        misil.dispose();
    }
}
