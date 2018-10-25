package com.mygdx.sprites;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.logic.texture.TextureImages;

public class Button extends BasicSprite {

    public Button(int x, int y, TextureImages texturePath, int width, int height) {
        super(x - width / 2, y - height / 2, 0, 0, texturePath.toString());
        textureWidth =  width;
        textureHeight = height;
    }

    public boolean isClicked(Vector2 touch) {
        return getSpriteRectangle().contains(touch);
    }
}
