package com.mygdx.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class Hero {
    private Vector3 position;
    private Vector3 velocity;

    private Texture hero;
    private TextureRegion heroMainRegion;
    private static final int GRAVITY = -10;

    public Hero(int x, int y) {
        this.position = new Vector3(x, y, 0);
        this.velocity = new Vector3(0, 0 , 0);
        hero = new Texture("gladiator_arena_sprites.gif");
        heroMainRegion = TextureRegion.split(hero, hero.getWidth() / 6, hero.getHeight() / 6)[0][0];
    }

    public void update(float dt) {
        velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(velocity.x, velocity.y, 0);
        velocity.scl(1 / dt);

        if (position.y < 0) {
            position.y = 0;
        }
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getHero() {
        return hero;
    }

    public TextureRegion getHeroMainRegion() {
        return heroMainRegion;
    }

    public void moveRight() {
        velocity.x = 100;
    }

    public void moveLeft() {
        velocity.x = -100;
    }

    public void jump() {
        if (position.y < 5) {
            velocity.y = 150;
        }
    }

    public void stop() {
        velocity.x = 0;
    }
}
