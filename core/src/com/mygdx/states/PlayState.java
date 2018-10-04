package com.mygdx.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MyGdxGame;
import com.mygdx.sprites.Hero;

public class PlayState extends State {

    Hero heroCharacter;
   // World world;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
//
//        world = new World(new Vector2(0, -20), true);
//        world.setContactListener(new ContactListener() {
//            @Override
//            public void beginContact(Contact contact) {
//
//            }
//
//            @Override
//            public void endContact(Contact contact) {
//
//            }
//
//            @Override
//            public void preSolve(Contact contact, Manifold oldManifold) {
//
//            }
//
//            @Override
//            public void postSolve(Contact contact, ContactImpulse impulse) {
//
//            }
//        });
        camera.setToOrtho(false, MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2);
        heroCharacter = new Hero(0, 0);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            System.out.println("HIT");
            heroCharacter.hit();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            heroCharacter.moveRight();
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            heroCharacter.moveLeft();
        } else if (!Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            heroCharacter.stop();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            heroCharacter.jump();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        heroCharacter.update(dt);

        camera.position.x = heroCharacter.getPosition().x;
        camera.update();
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
