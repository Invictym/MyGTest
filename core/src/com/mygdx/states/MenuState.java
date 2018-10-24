package com.mygdx.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.logic.files.FileWorker;

import java.io.File;
import java.util.Date;

public class MenuState extends State {

    private Texture backGround;
    private Texture playButton;

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        backGround = new Texture("background.jpg");
        playButton = new Texture("badlogic.jpg");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isTouched()) {
            gameStateManager.set(new SpaceState(gameStateManager));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        FileWorker.saveScore("a", "b", new Date());
        System.out.println(FileWorker.getUsersResults());
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(backGround, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        batch.draw(playButton,  MyGdxGame.WIDTH / 2 - playButton.getWidth(), MyGdxGame.HEIGHT / 2 - playButton.getHeight() / 2);
        batch.end();
    }

    @Override
    public void dispose() {
        backGround.dispose();
        playButton.dispose();
    }
}
