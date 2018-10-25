package com.mygdx.logic;

import com.mygdx.game.MyGdxGame;
import com.mygdx.logic.files.FileWorker;
import com.mygdx.logic.texture.TextureImages;
import com.mygdx.sprites.Meteorite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLogic {

    static float oldDt = 0;
    static float newDt = 0;
    static int meteoriteMap[][] = {
            {1, 0, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 0, 0, 0, 1},
            {1, 1, 0, 0, 0},
            {1, 0, 1, 0, 0},
            {1, 0, 0, 1, 0},
            {1, 0, 0, 0, 1},
            {0, 1, 1, 0, 0},
            {0, 1, 0, 1, 0},
            {0, 1, 0, 0, 1},
            {0, 0, 1, 1, 0},
            {0, 0, 1, 0, 1},
            {0, 0, 0, 1, 1},
            {1, 1, 1, 0, 0},
            {1, 1, 0, 1, 0},
            {1, 1, 0, 0, 1},
            {1, 0, 1, 1, 0},
            {1, 0, 1, 0, 1},
    };

    public static List<Meteorite> generateMeteorite(int width, int height, float dt) {
        newDt += dt;
        List<Meteorite> meteorites = new ArrayList<Meteorite>();
        if (newDt - oldDt > 1) {
            oldDt = newDt;
            Random randomLine = new Random();
            int meteoriteLine[] = meteoriteMap[randomLine.nextInt(meteoriteMap.length)];
            for (int i = 0; i < meteoriteLine.length; i++) {
                if (meteoriteLine[i] == 1) {
                    meteorites.add(new Meteorite(width / 5 * i, height + MyGdxGame.WIDTH / 10 * new Random().nextInt(4), -1000, TextureImages.METEORITE, MyGdxGame.WIDTH / 10));
                }
            }
        }
        return meteorites;
    }

    public static void saveResult(long result) {
        String resultSt = FileWorker.getUserScore();
        if (!resultSt.equals("0")) {
            long savedResult = Long.parseLong(resultSt);
            if (savedResult < result) {
                FileWorker.saveScore(result);
            }
        } else {
            FileWorker.saveScore(result);
        }
    }
}
