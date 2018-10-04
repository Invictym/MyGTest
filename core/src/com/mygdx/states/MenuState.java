package com.mygdx.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

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
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            gameStateManager.set(new PlayState(gameStateManager));
        }
        if (Gdx.input.isTouched()) {
            gameStateManager.set(new SpaceState(gameStateManager));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
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
