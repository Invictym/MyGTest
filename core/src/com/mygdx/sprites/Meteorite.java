package com.mygdx.sprites;


import com.mygdx.logic.texture.TextureImages;

public class Meteorite extends BasicSprite {

    public Meteorite(int x, int y, int speedY, TextureImages texturePath, int size) {
        super(x, y, 0, speedY, texturePath.toString());
        textureWidth = size;
        textureHeight = size;
    }
}
