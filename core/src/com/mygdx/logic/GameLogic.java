package com.mygdx.logic;

import com.mygdx.game.MyGdxGame;
import com.mygdx.sprites.Meteorite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLogic {

    static float oldDt = 0;
    static float newDt = 0;
    static boolean meteoriteMap[][] = {
            {false, false, true, false, true},
            {true, false, true, false, false},
            {false, false, true, true, false},
            {true, true, false, false, false},
            {false, true, false, true, false},
            {false, false, true, false, true},
            {false, true, false, false, true}
    } ;

    public static List<Meteorite> generateMeteorite(int width, int hight, float dt) {
        newDt += dt;
        List<Meteorite> meteorites = new ArrayList<Meteorite>();
        if (newDt - oldDt > 1) {
            oldDt = newDt;
            System.out.println(((int) newDt) + "//" + "create meteorite");
            Random randomLine = new Random();
            boolean meteoriteLine[] = meteoriteMap[randomLine.nextInt(meteoriteMap.length)];
            for (int i = 0; i < meteoriteLine.length; i++) {
                if (meteoriteLine[i]) {
                    meteorites.add(new Meteorite(width /5 * i , hight, -1000, "meteorite.png", MyGdxGame.WIDTH / 10));
                }
            }
        }
        return meteorites;
    }
}
