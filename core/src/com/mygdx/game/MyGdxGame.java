package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.states.GameStateManager;
import com.mygdx.states.MenuState;


public class MyGdxGame extends ApplicationAdapter {

    public static final int HEIGHT = 480;
    public static final int WIDTH = 800;

    SpriteBatch batch;
    private GameStateManager gameStateManager;


	@Override
	public void create () {
        gameStateManager = new GameStateManager();
        batch = new SpriteBatch();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        gameStateManager.push(new MenuState(gameStateManager));
	}



	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameStateManager.update(Gdx.graphics.getDeltaTime());
        gameStateManager.render(batch);
	}

	@Override
	public void dispose () {

	}
}
