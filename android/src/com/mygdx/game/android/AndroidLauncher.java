package com.mygdx.game.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.Somita;
import com.mygdx.game.assets.AssetHelper;

public class AndroidLauncher extends AndroidApplication {


	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        float density= getResources().getDisplayMetrics().density;
        new AssetHelper(density);
		initialize(new Somita(), config);
	}
}
