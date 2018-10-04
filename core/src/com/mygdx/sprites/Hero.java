package com.mygdx.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class Hero {
    private Vector3 position;
    private Vector3 velocity;
    private static int SPEED = 100;

    private Texture hero;
    private TextureRegion heroRegion;
    private TextureRegion[][] heroTextureRegions;
    private Animation walkAnim;
    private Animation hitAnimation;
    private static final int GRAVITY = -10;
    private boolean isMove = false;
    private boolean isHit = false;
    private float stateTime = 0;

    public Hero(int x, int y) {
        this.position = new Vector3(x, y, 0);
        this.velocity = new Vector3(0, 0 , 0);
        hero = new Texture("characters.png");
        heroTextureRegions = TextureRegion.split(hero, hero.getWidth() / 23, hero.getHeight() / 4);
        heroRegion = heroTextureRegions[1][1];
        walkAnim = new Animation(0.15f, heroTextureRegions[1][0], heroTextureRegions[1][1], heroTextureRegions[1][2], heroTextureRegions[1][3], heroTextureRegions[1][4]);
        hitAnimation = new Animation(0.33f, heroTextureRegions[1][11], heroTextureRegions[1][13], heroTextureRegions[1][11]);
    }

    public void update(float dt) {

        velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(velocity.x, velocity.y, 0);
        velocity.scl(1 / dt);

        if (isHit) {
            stateTime += dt;
            heroRegion = (TextureRegion) hitAnimation.getKeyFrame(stateTime, false);
            if (hitAnimation.isAnimationFinished(stateTime)) {
                isHit = false;
                stateTime = 0;
            }
        } else if (isMove) {
            stateTime += dt;
            heroRegion = (TextureRegion) walkAnim.getKeyFrame(stateTime, true);
        } else {
            heroRegion = heroTextureRegions[1][0];
            stateTime = 0;
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
        velocity.x = SPEED;
        if (heroRegion.isFlipX()) {
            heroRegion.flip(true, false);
        }
        isMove = true;
    }

    public void moveLeft() {
        velocity.x = -SPEED;
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

    public void hit() {
        isHit = true;
    }

    public void stop() {
        velocity.x = 0;
        isMove = false;
    }
}
