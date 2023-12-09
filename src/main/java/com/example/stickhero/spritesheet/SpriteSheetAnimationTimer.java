package com.example.stickhero.spritesheet;

import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;

import java.util.Collections;
import java.util.List;

public class SpriteSheetAnimationTimer extends CustomAnimationTimer {
    private final SpriteSheet spriteSheet;
    private final List<Rectangle2D> crops;
    private int current = 0;

    public SpriteSheetAnimationTimer(SpriteSheet spriteSheet, List<Rectangle2D> crops, long animationDurationNs) {
        super(animationDurationNs/crops.size());
        this.spriteSheet = spriteSheet;
        this.crops = crops;
    }

    @Override
    public void start() {
        current = crops.size()-1;
        handle();
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
        current = crops.size()-1;
        handle();
    }

    @Override
    public void handle() {
        current = (current + 1) % crops.size();
        spriteSheet.setViewport(crops.get(current));
    }
}

