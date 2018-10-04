package com.mygdx.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGdxGame;
import com.mygdx.states.SpaceState;

public class Ship {

    private Vector3 position;
    private Vector3 velocity;
    private Texture texture;
    private int SPEED;

    public Ship(int x, int y, int speed) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        SPEED = speed;
        texture = new Texture("ship.png");
    }

    public void update(float dt) {
        velocity.add(0, 0, 0);
        velocity.scl(dt);
        position.add(velocity.x, 0, 0);
        velocity.scl(1 / dt);

        if (position.x < 0) {
            position.x = 0;
        }

        if (position.x > MyGdxGame.WIDTH - SpaceState.SHIP_SIZE) {
            position.x = MyGdxGame.WIDTH - SpaceState.SHIP_SIZE;
        }
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public Texture getTexture() {
        return texture;
    }

    public void moveRight() {
        velocity.x = SPEED;
    }

    public void moveLeft() {
        velocity.x = -SPEED;
    }

    public void stop() {
        velocity.x = 0;
    }

    public void dispose() {
        texture.dispose();
    }
}
