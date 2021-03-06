package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.states.GameStateManager;
import com.mygdx.states.MenuState;


public class MyGdxGame extends ApplicationAdapter {

    public static int HEIGHT = 800;
    public static int WIDTH = 480;

    SpriteBatch batch;
    private GameStateManager gameStateManager;


    @Override
	public void create () {
        gameStateManager = new GameStateManager();
        System.out.println(Gdx.graphics.getWidth() + " WIDTH");
        HEIGHT = Gdx.graphics.getHeight();
        WIDTH = Gdx.graphics.getWidth();
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
