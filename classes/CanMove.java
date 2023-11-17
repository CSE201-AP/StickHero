package com.example.stickhero.structure;

import javafx.geometry.Point2D;
import javafx.scene.Node;

public class CanMove implements MovementAnimator {
    private final double speed;
    private final Node node;
    private final AnimatorEvent before;
    private final AnimatorEvent after;

    public CanMove(Node node, double speed) {
    }

    public CanMove(Node node, double speed, AnimatorEvent before, AnimatorEvent after) {
    }

    @Override
    public void moveBy(Point2D offset) {
    }

    @Override
    public void moveTo(Point2D target) {
    }
}