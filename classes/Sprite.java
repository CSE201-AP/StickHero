package com.example.stickhero.structure;

import javafx.scene.Node;

public abstract class Sprite {
    protected Node node;
    private MovementAnimator movementAnimator;
    private RotationAnimator rotationAnimator;
    private ScaleAnimator scaleAnimator;

    public Sprite(Node node, MovementAnimator movementAnimator, RotationAnimator rotationAnimator, ScaleAnimator scaleAnimator) {
    }

    public MovementAnimator movementAnimator() {
    }

    public ScaleAnimator scaleAnimator() {
    }

    public RotationAnimator rotationAnimator() {
    }
}