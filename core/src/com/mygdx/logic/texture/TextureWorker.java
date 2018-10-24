package com.mygdx.logic.texture;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class TextureWorker {

    private static Map<String, Texture> textureMap = new HashMap<String, Texture>();

    public static Texture getTexture(String path) {
        Texture texture = textureMap.get(path);
        System.out.println("Path " + path);
        if (texture == null) {
            System.out.println("TX" + texture);
          texture = new Texture(path);
          textureMap.put(path, texture);
        }
        System.out.println(textureMap.size());
        return texture;
    }

    public static void dispose(String image) {
        System.out.println("Dispose " + image);
        Texture texture = textureMap.get(image);
        if (texture != null) {
            texture.dispose();
            textureMap.remove(image);
        }
    }
}
