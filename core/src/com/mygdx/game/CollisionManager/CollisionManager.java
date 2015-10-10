package com.mygdx.game.CollisionManager;

import com.mygdx.game.Model.Helicopter;
import com.mygdx.game.Model.Misil;
import com.mygdx.game.Model.Vendaje;

/**
 * Created by USER on 05/10/2015.
 */
public class CollisionManager {

    public static boolean isCollisionMisil(Helicopter helicopter, Misil[] misils){
        for (int i = 0; i < misils.length; i++) {
            if(helicopter.getBounds().overlaps(misils[i].getBounds())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCollisionVendaje(Helicopter helicopter, Vendaje[] vendajes){
        for (int i = 0; i < vendajes.length; i++) {
            if(helicopter.getBounds().overlaps(vendajes[i].getBounds())) {
                vendajes[i].hasCollided();
                return true;
            }
        }
        return false;

    }

}
