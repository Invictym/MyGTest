package com.mygdx.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.logic.files.FileWorker;
import com.mygdx.logic.texture.TextureImages;
import com.mygdx.logic.texture.TextureWorker;
import com.mygdx.sprites.Button;


public class MenuState extends State {

    private Texture backGround;
    private Button playButton;
    private Button resultButton;
    private Button exitButton;
    private BitmapFont font;
    private Vector2 touch = new Vector2();
    private int buttonWidth = MyGdxGame.WIDTH / 2;
    private int buttonHeight = MyGdxGame.HEIGHT / 5;
    private String scoreCount;
    private String scoreText = "SCORE\n";

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        backGround = new Texture("background.jpg");
        playButton = new Button(MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2, TextureImages.START_BUTTON, buttonWidth, buttonHeight);
        resultButton = new Button(MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2 - buttonHeight, TextureImages.RESULT_BUTTON, buttonWidth, buttonHeight);
        exitButton = new Button(MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2 -  2 * buttonHeight, TextureImages.EXIT_BUTTON, buttonWidth, buttonHeight);
        scoreCount = FileWorker.getUserScore();
        font = new BitmapFont();
        font.setColor(Color.YELLOW);
        font.getData().setScale(5f);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isTouched()) {
            touch.set(MyGdxGame.WIDTH - Gdx.input.getX(), MyGdxGame.HEIGHT - Gdx.input.getY());
            if (playButton.isClicked(touch)) {
                gameStateManager.set(new SpaceState(gameStateManager));
            } else if (resultButton.isClicked(touch)) {
                System.out.println("RESULT");
            } else if (exitButton.isClicked(touch)) {
                System.out.println("EXIT click");

            }
        }
    }

    @Override
    public void update(float dt) {
        touch.set(0,0);
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(backGround, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        batch.draw(TextureWorker.getTexture(playButton.getTextureName()), playButton.getPosition().x, playButton.getPosition().y, playButton.getTextureWidth(), playButton.getTextureHeight());
        batch.draw(TextureWorker.getTexture(resultButton.getTextureName()), resultButton.getPosition().x, resultButton.getPosition().y, resultButton.getTextureWidth(), resultButton.getTextureHeight());
        batch.draw(TextureWorker.getTexture(exitButton.getTextureName()), exitButton.getPosition().x, exitButton.getPosition().y, exitButton.getTextureWidth(), exitButton.getTextureHeight());
        font.draw(batch, scoreText + scoreCount, MyGdxGame.WIDTH / 2, 3 * MyGdxGame.HEIGHT / 4);
        batch.end();
    }

    @Override
    public void dispose() {
        backGround.dispose();
        TextureWorker.dispose(playButton.getTextureName());
        TextureWorker.dispose(resultButton.getTextureName());
        TextureWorker.dispose(exitButton.getTextureName());
        font.dispose();
    }
}
