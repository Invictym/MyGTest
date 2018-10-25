package com.mygdx.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.logic.GameLogic;
import com.mygdx.logic.files.FileWorker;
import com.mygdx.logic.texture.TextureImages;
import com.mygdx.logic.texture.TextureWorker;
import com.mygdx.sprites.Meteorite;
import com.mygdx.sprites.Ship;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpaceState extends State {

    private Ship ship;
    private List<Meteorite> meteorites = new ArrayList<Meteorite>();
    private Texture background;
    private String scoreText = "SCORE\n";
    private long scoreCount = 0;
    private BitmapFont font;
    private double rotationLevel = 0.3;

    public SpaceState(GameStateManager gameStateManager) {
        super(gameStateManager);
        camera.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        background = new Texture("background.jpg");
        ship = new Ship(MyGdxGame.WIDTH / 2, 0, MyGdxGame.WIDTH, TextureImages.SHIP, MyGdxGame.WIDTH / 6);
        font = new BitmapFont();
        font.setColor(Color.YELLOW);
        font.getData().setScale(5f);
    }

    @Override
    protected void handleInput() {
        System.out.println("INPUT " + System.currentTimeMillis());
        if (Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer)) {
            float x = Gdx.input.getAccelerometerX();
            if (x > rotationLevel) {
                ship.moveLeft();
            } else if (x < -rotationLevel) {
                ship.moveRight();
            } else if (x > -rotationLevel && x < rotationLevel) {
                ship.stop();
            }
        }

        if (Gdx.input.isTouched()) {
            /*
             *
             * Need add logic for shooting
             *
             * */
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        ship.update(dt);
        meteorites.addAll(GameLogic.generateMeteorite(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, dt));
        for (int i = 0; i < meteorites.size(); i++) {
            meteorites.get(i).update(dt);
            if (meteorites.get(i).getPosition().y < -meteorites.get(i).getTextureWidth()) {
                meteorites.remove(i);
            }

            if (meteorites.get(i).isTouch(ship.getSpriteRectangle())) {
                GameLogic.saveResult(scoreCount);
                gameStateManager.set(new MenuState(gameStateManager));
            }
        }
        scoreCount += dt * 100;
        camera.update();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        int x = MyGdxGame.HEIGHT / background.getHeight() + 1;
        batch.draw(background, 0, 0, background.getWidth() * x, background.getHeight() * x);
        batch.draw(TextureWorker.getTexture(ship.getTextureName()), ship.getPosition().x, ship.getPosition().y, ship.getTextureWidth(), ship.getTextureHeight());
        for (Meteorite meteorite : meteorites) {
            batch.draw(TextureWorker.getTexture(meteorite.getTextureName()), meteorite.getPosition().x, meteorite.getPosition().y, meteorite.getTextureWidth(), meteorite.getTextureHeight());
        }
        font.draw(batch, scoreText + scoreCount, MyGdxGame.WIDTH - 300, MyGdxGame.HEIGHT - 100);
        batch.end();
    }

    @Override
    public void dispose() {
        TextureWorker.dispose(TextureImages.SHIP.toString());
        TextureWorker.dispose(TextureImages.METEORITE.toString());
    }
}
