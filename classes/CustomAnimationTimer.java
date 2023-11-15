package com.example.stickhero.structure;

import javafx.animation.AnimationTimer;

public abstract class CustomAnimationTimer extends AnimationTimer {
    private final long lengthNs;
    private long lastTime;

    public CustomAnimationTimer(long lengthNs) {

    }

    @Override
    public void handle(long now) {

    }

    public abstract void handle();
}