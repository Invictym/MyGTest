package com.mygdx.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.logic.GameLogic;
import com.mygdx.logic.TextureWorker;
import com.mygdx.sprites.Meteorite;
import com.mygdx.sprites.Ship;

import java.util.ArrayList;
import java.util.List;

public class SpaceState extends State {

    private Ship ship;
    private List<Meteorite> meteorites = new ArrayList<Meteorite>();
    private Texture background;

    public SpaceState(GameStateManager gameStateManager) {
        super(gameStateManager);
        camera.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        background = new Texture("background.jpg");
        ship = new Ship(MyGdxGame.WIDTH / 2, 0, MyGdxGame.WIDTH, "ship.png", MyGdxGame.WIDTH / 6);
    }

    @Override
    protected void handleInput() {

        if (Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer)) {
           float x = Gdx.input.getAccelerometerX();
            if (x > 0.5) {
                ship.moveLeft();
            } else if (x < -0.5) {
                ship.moveRight();
            } else if (x > -0.5 && x < 0.5) {
                ship.stop();
            }
        }

        if (Gdx.input.isTouched()) {

        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        ship.update(dt);
        meteorites.addAll(GameLogic.generateMeteorite(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, dt));
        System.out.println("metiorite" + meteorites);
        for (int i =0; i < meteorites.size(); i++) {
            meteorites.get(i).update(dt);
            if (meteorites.get(i).getPosition().y < -10) {
                System.out.println(meteorites.get(i).getPosition().y);
                meteorites.remove(i);
            }
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        int x = MyGdxGame.HEIGHT / background.getHeight() + 1;
        batch.draw(background, 0, 0, background.getWidth() * x, background.getHeight() * x);
        batch.draw(TextureWorker.getTexture(ship.getTextureName()), ship.getPosition().x, ship.getPosition().y, ship.getTextureWidth(), ship.getTextureWidth());
        for (Meteorite meteorite : meteorites) {
            batch.draw(TextureWorker.getTexture(meteorite.getTextureName()), meteorite.getPosition().x, meteorite.getPosition().y,  meteorite.getTextureWidth(), meteorite.getTextureWidth());
        }
        batch.end();
    }

    @Override
    public void dispose() {
        TextureWorker.dispose();
    }
}
