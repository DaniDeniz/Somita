package com.mygdx.game.assets;

/**
 * Created by USER on 02/10/2015.
 */
public class AssetHelper {

    private static int widthPixels;
    private static int heightPixels;


    public AssetHelper(int w, int h) {
        this.widthPixels = w;
        this.heightPixels = h;
    }

    public static int getWidthPixels() {
        return widthPixels;
    }

    public static int getHeightPixels() {
        return heightPixels;
    }
}
