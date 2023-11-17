package com.example.stickhero.structure;

import javafx.scene.Node;

public class CanRotate implements RotationAnimator {
    private final double speed;
    private final Node node;
    private final AnimatorEvent before;
    private final AnimatorEvent after;

    public CanRotate(Node node, double speed) {
    }

    public CanRotate(Node node, double speed, AnimatorEvent before, AnimatorEvent after) {
    }

    @Override
    public void rotateTo(double angle) {
    }

    @Override
    public void rotateTo(double angle, double pivotX, double pivotY) {
    }

    @Override
    public void rotateBy(double theta) {
    }

    @Override
    public void rotateBy(double theta, double pivotX, double pivotY) {
    }
}