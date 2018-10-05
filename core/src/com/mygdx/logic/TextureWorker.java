package com.mygdx.logic;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TextureWorker {

    private static Map<String, Texture> textureMap = new HashMap<String, Texture>();

    public static Texture getTexture(String path) {
        Texture texture = textureMap.get(path);
        if (texture == null) {
          texture = new Texture(path);
          textureMap.put(path, texture);
        }
        return texture;
    }

    public static void dispose() {
        Iterator iterator = textureMap.entrySet().iterator();
        while (iterator.hasNext()) {
            textureMap.get(iterator).dispose();
        }
    }
}
