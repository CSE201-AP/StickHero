package com.example.stickhero.structure;

import java.util.List;

public class Foreground implements Pannable {
    private final List<Sprite> sprites;

    public Foreground(List<Sprite> sprites) {

    }

    public List<Sprite> getSprites() {
        return sprites;
    }

    @Override
    public void panHorizontal(double offset) {

    }
}