package com.mygdx.logic.texture;

public enum TextureImages {
    BACKGROUND("background.jpg"), SHIP("ship.png"), METEORITE("meteorite.png");

    private String text;

    TextureImages(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
