package com.mygdx.logic.texture;

public enum TextureImages {
    BACKGROUND("background.jpg"), SHIP("ship.png"), METEORITE("meteorite.png"), START_BUTTON("start_button.png"), EXIT_BUTTON("exit_button.png"), RESULT_BUTTON("result_button.png");

    private String text;

    TextureImages(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
