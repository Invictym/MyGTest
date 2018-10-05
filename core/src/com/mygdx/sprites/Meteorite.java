package com.mygdx.sprites;


public class Meteorite extends BasicSprite{

    public Meteorite(int x, int y,int speedY, String texturePath, int size) {
        super(x, y, 0, speedY, texturePath);
        textureWidth = size;
    }
}
