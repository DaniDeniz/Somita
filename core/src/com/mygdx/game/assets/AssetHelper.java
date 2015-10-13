package com.mygdx.game.assets;

/**
 * Created by USER on 02/10/2015.
 */
public class AssetHelper {

    private static int widthPixels;
    private static int heightPixels;
    private static float density;

    public static float getDensity() {
        return density;
    }



    public AssetHelper(int w, int h, float density) {
        this.widthPixels =w;
        this.heightPixels =h;
        this.density =density;
    }

    public static int getWidthPixels() {
        return widthPixels;
    }

    public static int getHeightPixels() {
        return heightPixels;
    }
}
