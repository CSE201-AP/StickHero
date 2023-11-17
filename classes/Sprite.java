package com.example.stickhero.structure;

import javafx.scene.Node;

public abstract class Sprite {
    protected final Node node;
    private MovementAnimator movementAnimator;
    private RotationAnimator rotationAnimator;
    private ScaleAnimator scaleAnimator;

    public Sprite(Node node, MovementAnimator movementAnimator, RotationAnimator rotationAnimator, ScaleAnimator scaleAnimator) {
    }

    public MovementAnimator getMovementAnimator() {
    }

    public void setMovementAnimator(MovementAnimator movementAnimator) {

    }

    public ScaleAnimator getScaleAnimator() {
    }

    public void setScaleAnimator(ScaleAnimator scaleAnimator) {

    }

    public RotationAnimator getRotationAnimator() {
    }

    public void setRotationAnimator(RotationAnimator rotationAnimator) {

    }
}