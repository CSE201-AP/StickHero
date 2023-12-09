package com.example.stickhero.spritesheet;

import javafx.geometry.Rectangle2D;

import java.util.List;

public class SpriteSheetGroup {
    public List<Rectangle2D> sprites;
    public long duration;
    public double speed;
    public SpriteSheetGroup(List<Rectangle2D> sprites, long duration, double speed) {
        this.sprites = sprites;
        this.duration = duration;
        this.speed = speed;
    }
}
