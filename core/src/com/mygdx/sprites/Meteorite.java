package com.mygdx.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Meteorite {

    private Vector3 position;
    private Vector3 velocity;
    private Texture texture;

    public Meteorite(int x, int y, int speed) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, speed, 0);
        texture = new Texture("meteorite.png");
    }

    public void update(float dt) {
        velocity.add(0, 0, 0);
        velocity.scl(dt);
        position.add(0, velocity.y, 0);
        velocity.scl(1 / dt);

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

    public void dispose() {
        texture.dispose();
    }
}
