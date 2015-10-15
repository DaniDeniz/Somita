package com.mygdx.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;




/**
 * Created by USER on 28/09/2015.
 */
public class AssetLoader {
    public static Texture explosion,bgIzq,bgDer;
    public static Texture helicopter,misil,vendaje,misilInvertor;
    public static TextureRegion helicopterStopped;
    public static Animation helicopterMove,helicopterExplosion;
    public static Sound rambo,unoHelicoptero,soma,ezequie,milCosa;

    public static BitmapFont font,shadow;

    public static Preferences pref;




    public static void load(){
        helicopter = new Texture("helicoptero.png");
        misil = new Texture("misilNuclear.png");
        explosion= new Texture("explosion.png");
        vendaje= new Texture("vendaje.png");
        misilInvertor = new Texture("misilInversor.png");

        helicopterStopped = new TextureRegion(helicopter,450,213);

        bgIzq = new Texture("BgHD.png");
        bgDer = new Texture("BgHDInv.png");

        TextureRegion[][] regions = TextureRegion.split(helicopter, 450, 213);
        TextureRegion[] helicopterMoving = regions[0];
        helicopterMove = new Animation(0.07f,helicopterMoving);

        TextureRegion[][] regionsE = TextureRegion.split(explosion, 190, 150);
        TextureRegion[] helicopterExploding = regionsE[0];
        helicopterExplosion = new Animation(0.1f,helicopterExploding);

        rambo = Gdx.audio.newSound(Gdx.files.internal("rambo.mp3"));
        soma = Gdx.audio.newSound(Gdx.files.internal("pasosoma.mp3"));
        unoHelicoptero = Gdx.audio.newSound(Gdx.files.internal("trinchera.wav"));
        ezequie = Gdx.audio.newSound(Gdx.files.internal("pasoezequie.mp3"));
        milCosa = Gdx.audio.newSound(Gdx.files.internal("milcosa.wav"));

        pref= Gdx.app.getPreferences("Soma");

        if(!pref.contains("HighScore")){
            pref.putInteger("HighScore",0);
            pref.flush();
        }

        font = new BitmapFont(Gdx.files.internal("text.fnt"),Gdx.files.internal("text.png"),false);
        font.getData().setScale(.4f,.4f);

        shadow = new BitmapFont(Gdx.files.internal("shadow.fnt"),Gdx.files.internal("shadow.png"),false);
        shadow.getData().setScale(.4f,.4f);


    }

    public float getFontX( BitmapFont font , CharSequence str )    {
        float ancho_str = font.getScaleX()*str.length();
        float fontX = Gdx.graphics.getWidth()/2 - ancho_str/2;
        return fontX;
        }

    public static void dispose(){
        vendaje.dispose();
        helicopter.dispose();
        misil.dispose();
        bgIzq.dispose();
        bgDer.dispose();
        explosion.dispose();
        rambo.dispose();
        unoHelicoptero.dispose();
        soma.dispose();
        ezequie.dispose();
        milCosa.dispose();
    }
}
