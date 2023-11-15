package com.example.stickhero.structure;

import javafx.scene.Node;

public class CanRotate implements RotationAnimator {
    private double speed;
    private Node node;
    private AnimatorEvent before;
    private AnimatorEvent after;

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