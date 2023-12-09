package com.example.stickhero.spritesheet;

import javafx.animation.AnimationTimer;

public abstract class CustomAnimationTimer extends AnimationTimer {
    private final long lengthNs;
    private long lastTime = -1;

    public CustomAnimationTimer(long lengthNs) {
        this.lengthNs = lengthNs;
    }

    @Override
    public void handle(long now) {
        if (lastTime == -1) lastTime = now - lengthNs;
        if (now - lastTime >= lengthNs) {
            handle();
            lastTime = now;
        }
    }

    public abstract void handle();
}
