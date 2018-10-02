package com.mygdx.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class Hero {
    private Vector3 position;
    private Vector3 velocity;

    private Texture hero;
    private TextureRegion heroRegion;
    private TextureRegion[][] heroTextureRegions;
    private Animation herAnim;
    private static final int GRAVITY = -10;
    private boolean isMove = false;

    public Hero(int x, int y) {
        this.position = new Vector3(x, y, 0);
        this.velocity = new Vector3(0, 0 , 0);
        hero = new Texture("gladiator_arena_sprites.gif");
        heroTextureRegions = TextureRegion.split(hero, hero.getWidth() / 6, hero.getHeight() / 6);
        heroRegion = heroTextureRegions[0][0];
        herAnim = new Animation(0.025f, heroTextureRegions[0][1], heroTextureRegions[0][2], heroTextureRegions[0][3], heroTextureRegions[0][4]);
    }

    public void update(float dt) {
        velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(velocity.x, velocity.y, 0);
        velocity.scl(1 / dt);

        if (isMove) {
            heroRegion = (TextureRegion) herAnim.getKeyFrame(dt);
        } else {

        }

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
        return heroRegion;
    }

    public void moveRight() {
        velocity.x = 100;
        if (heroRegion.isFlipX()) {
            heroRegion.flip(true, false);
        }
        isMove = true;
    }

    public void moveLeft() {
        velocity.x = -100;
        if (!heroRegion.isFlipX()) {
            heroRegion.flip(true, false);
        }
        isMove = true;
    }

    public void jump() {
        if (position.y < 5) {
            velocity.y = 150;
        }
    }

    public void stop() {
        velocity.x = 0;
        isMove = false;
    }
}
