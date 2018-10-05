package com.mygdx.sprites;

import com.mygdx.game.MyGdxGame;

public class Ship extends BasicSprite {

    private int speed;

    public Ship(int x, int y, int speed, String texturePath, int size) {
        super(x, y, 0, 0, texturePath);
        this.speed = speed;
        this.textureWidth = size;
    }

    @Override
    public void update(float dt) {
        velocity.add(0, 0, 0);
        velocity.scl(dt);
        position.add(velocity.x, 0, 0);
        velocity.scl(1 / dt);

        if (position.x < 0) {
            position.x = 0;
        }

        if (position.x > MyGdxGame.WIDTH - textureWidth) {
            position.x = MyGdxGame.WIDTH - textureWidth;
        }
    }

    public void moveRight() {
        velocity.x = speed;
    }

    public void moveLeft() {
        velocity.x = -speed;
    }

    public void stop() {
        velocity.x = 0;
    }

}
