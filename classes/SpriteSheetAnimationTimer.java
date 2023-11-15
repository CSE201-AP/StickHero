package com.example.stickhero.structure;

import javafx.geometry.Rectangle2D;

import java.util.List;

public class SpriteSheetAnimationTimer extends CustomAnimationTimer {
    public SpriteSheet spriteSheet;
    private List<Rectangle2D> crops;
    private long animationDurationNs;
    private int current;

    public SpriteSheetAnimationTimer(SpriteSheet spriteSheet, List<Rectangle2D> crops, long animationDurationNs) {
    }

    @Override
    public void handle() {
    }

    public void reset() {
    }
}