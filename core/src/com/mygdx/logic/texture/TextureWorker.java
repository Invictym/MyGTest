package com.mygdx.logic.texture;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class TextureWorker {

    private static Map<String, Texture> textureMap = new HashMap<String, Texture>();

    public static Texture getTexture(TextureImages path) {
        Texture texture = textureMap.get(path);
        if (texture == null) {
          texture = new Texture(path.toString());
          textureMap.put(path.toString(), texture);
        }
        System.out.println(textureMap.size());
        return texture;
    }

    public static void dispose(TextureImages image) {
        System.out.println("Dispose " + image);
        Texture texture = textureMap.get(image.toString());
        if (texture != null) {
            texture.dispose();
        }
    }
}
