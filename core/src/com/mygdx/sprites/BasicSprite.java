package com.mygdx.sprites;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.logic.texture.TextureImages;


public abstract class BasicSprite {

    protected Vector3 position;
    protected Vector3 velocity;
    protected TextureImages texture;
    protected int textureWidth;


    public BasicSprite(int x, int y, int speedX, int speedY, TextureImages texturePath) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(speedX, speedY, 0);
        texture = texturePath;
    }

    public void update(float dt) {
        velocity.add(0, 0, 0);
        velocity.scl(dt);
        position.add(velocity.x, velocity.y, 0);
        velocity.scl(1 / dt);

    }

    public boolean isTouch(Rectangle rectangle) {
        return rectangle.overlaps(getSpriteRectangle());
    }

    public Rectangle getSpriteRectangle() {
        return new Rectangle((int) position.x  + textureWidth / 4, (int) position.y  + textureWidth / 4, textureWidth,  textureWidth);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public TextureImages getTextureName() {
        return texture;
    }

    public int getTextureWidth() {
        return textureWidth;
    }
}
