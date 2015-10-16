package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.assets.AssetLoader;
import com.mygdx.game.screens.GameScreen;

public class Somita extends Game {


    @Override
    public void create() {
        AssetLoader.load();
        setScreen(new GameScreen());
    }

    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }


}
