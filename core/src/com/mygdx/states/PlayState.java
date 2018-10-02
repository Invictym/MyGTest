package com.mygdx.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.sprites.Hero;

public class PlayState extends State {

    Hero heroCharacter;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);

        camera.setToOrtho(false, MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2);
        heroCharacter = new Hero(0, 0);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            heroCharacter.moveRight();
        } else if (!Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            heroCharacter.stop();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            heroCharacter.moveLeft();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            heroCharacter.jump();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        heroCharacter.update(dt);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(heroCharacter.getHeroMainRegion(), heroCharacter.getPosition().x, heroCharacter.getPosition().y);
        batch.end();

    }

    @Override
    public void dispose() {

    }
}
