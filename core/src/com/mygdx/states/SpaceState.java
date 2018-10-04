package com.mygdx.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.logic.GameLogic;
import com.mygdx.sprites.Meteorite;
import com.mygdx.sprites.Ship;

import java.util.ArrayList;
import java.util.List;

public class SpaceState extends State {

    Ship ship;
    List<Meteorite> meteorites = new ArrayList<Meteorite>();
    public static int SHIP_SIZE = 30;

    public SpaceState(GameStateManager gameStateManager) {
        super(gameStateManager);
        SHIP_SIZE = MyGdxGame.WIDTH / 6;
        camera.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        ship = new Ship(0, 0, MyGdxGame.WIDTH);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isTouched()) {
            float positionsDelta = Gdx.input.getX() - ship.getPosition().x;
            System.out.println(Gdx.input.getX() + " // " + ship.getPosition().x + " // " + positionsDelta);
                if (positionsDelta > 10 + SHIP_SIZE / 2) {
                ship.moveRight();
            } else if (positionsDelta < -10 + SHIP_SIZE / 2) {
                ship.moveLeft();
            } else if (positionsDelta > -10 + SHIP_SIZE / 2 && positionsDelta < 10 + SHIP_SIZE / 2) {
                ship.stop();
            }
        } else {
            ship.stop();
        }


    }

    @Override
    public void update(float dt) {
        handleInput();
        ship.update(dt);
        meteorites.addAll(GameLogic.generateMeteorite(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, dt));
        System.out.println("metiorite" + meteorites);
        for (Meteorite meteorite : meteorites) {
            meteorite.update(dt);
            if (meteorite.getPosition().y - MyGdxGame.HEIGHT < 0) {
                //meteorites.remove(meteorite);
            }
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(ship.getTexture(), ship.getPosition().x, ship.getPosition().y, SHIP_SIZE, SHIP_SIZE);
        for (Meteorite meteorite : meteorites) {
            batch.draw(meteorite.getTexture(), meteorite.getPosition().x, meteorite.getPosition().y,  SHIP_SIZE, SHIP_SIZE);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        ship.dispose();
        for (Meteorite meteorite : meteorites) {
           meteorite.dispose();
        }
    }
}
