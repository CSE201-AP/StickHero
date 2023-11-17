package com.example.stickhero.structure;

import javafx.geometry.Rectangle2D;

import java.util.List;

public class SpriteSheetAnimationTimer extends CustomAnimationTimer {
    private final SpriteSheet spriteSheet;
    private final List<Rectangle2D> crops;
    private final long animationDurationNs;
    private int current;

    public SpriteSheetAnimationTimer(SpriteSheet spriteSheet, List<Rectangle2D> crops, long animationDurationNs) {
    }

    @Override
    public void handle() {
    }

    public void reset() {
    }
}