package com.mygdx.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
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
    public static Texture explosion, explosionInv, bgIzq, bgDer;
    public static Texture helicopter, misil, vendaje, misilInvertor;
    public static TextureRegion helicopterStopped;
    public static Animation helicopterMove, helicopterExplosion, explosionInvertor;
    public static Sound rambo, unoHelicoptero, soma, ezequie, milCosa, explosionSound, explosionInvSound;
    public static Music musicSound, musicMisilInv;

    public static BitmapFont font, shadow;

    public static Preferences pref;


    public static void load() {
        loadHelicopter();
        loadMisil();
        loadExplosion();
        loadMisilInversor();
        loadMisilInvExplosion();
        loadVendaje();
        loadBackground();
        loadSounds();
        loadFonts();
        loadPreferences();
    }

    public float getFontX(BitmapFont font, CharSequence str) {
        float ancho_str = font.getScaleX() * str.length();
        float fontX = Gdx.graphics.getWidth() / 2 - ancho_str / 2;
        return fontX;
    }

    private static void loadHelicopter(){
        helicopter = new Texture("helicoptero.png");
        helicopterStopped = new TextureRegion(helicopter, 450, 213);
        TextureRegion[][] regions = TextureRegion.split(helicopter, 450, 213);
        TextureRegion[] helicopterMoving = regions[0];
        helicopterMove = new Animation(0.07f, helicopterMoving);
    }

    private static void loadMisil(){
        misil = new Texture("misilNuclear.png");
    }

    private static void loadExplosion(){
        explosion = new Texture("explosion.png");
        TextureRegion[][] regionsE = TextureRegion.split(explosion, 190, 150);
        TextureRegion[] helicopterExploding = regionsE[0];
        helicopterExplosion = new Animation(0.1f, helicopterExploding);
    }

    private  static void loadMisilInversor(){
        misilInvertor = new Texture("misilInversor.png");
    }

    private static void loadMisilInvExplosion(){
        explosionInv = new Texture("explosionInv.png");
        TextureRegion[][] regionsEI = TextureRegion.split(explosionInv, 190, 150);
        TextureRegion[] helicopterInverting = regionsEI[0];
        explosionInvertor = new Animation(0.08f, helicopterInverting);

    }

    private static void loadVendaje(){
        vendaje = new Texture("vendajeAmarillo.png");
    }

    private static void loadBackground(){
        bgIzq = new Texture("BgHD.png");
        bgDer = new Texture("BgHDInv.png");
    }

    private static void loadSounds(){
        rambo = Gdx.audio.newSound(Gdx.files.internal("rambo.mp3"));
        rambo.setVolume(1,2);
        soma = Gdx.audio.newSound(Gdx.files.internal("pasosoma.mp3"));
        unoHelicoptero = Gdx.audio.newSound(Gdx.files.internal("trinchera.wav"));
        ezequie = Gdx.audio.newSound(Gdx.files.internal("pasoezequie.mp3"));
        milCosa = Gdx.audio.newSound(Gdx.files.internal("milcosa.wav"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("Explosion_Debris.wav"));
        explosionInvSound = Gdx.audio.newSound(Gdx.files.internal("Effect05.WAV"));
        musicSound =Gdx.audio.newMusic(Gdx.files.internal("PuckerUp.mp3"));
        musicSound.setVolume(0.09f);
        musicSound.play();
        musicMisilInv = Gdx.audio.newMusic(Gdx.files.internal("Mr_Pink.mp3"));
        musicMisilInv.setVolume(0.09f);
    }

    private static void loadFonts(){
        font = new BitmapFont(Gdx.files.internal("text.fnt"), Gdx.files.internal("text.png"), false);
        font.getData().setScale(.4f, .4f);

        shadow = new BitmapFont(Gdx.files.internal("shadow.fnt"), Gdx.files.internal("shadow.png"), false);
        shadow.getData().setScale(.4f, .4f);
    }

    private static void loadPreferences(){
        pref = Gdx.app.getPreferences("Soma");

        if (!pref.contains("HighScore")) {
            pref.putInteger("HighScore", 0);
            pref.flush();
        }
    }

    public static void dispose() {
        vendaje.dispose();
        helicopter.dispose();
        misil.dispose();
        bgIzq.dispose();
        bgDer.dispose();
        explosion.dispose();
        rambo.dispose();
        unoHelicoptero.dispose();
        musicSound.stop();
        musicSound.dispose();
        explosionSound.dispose();
        soma.dispose();
        ezequie.dispose();
        milCosa.dispose();
        explosionSound.dispose();
        font.dispose();
        shadow.dispose();
        misilInvertor.dispose();
        explosionInv.dispose();
        musicMisilInv.dispose();
        explosionInvSound.dispose();
    }
}
