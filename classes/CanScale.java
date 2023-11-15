package com.example.stickhero.structure;

import javafx.scene.Node;

public class CanScale implements ScaleAnimator {
    private double speed;
    private Node node;
    private AnimatorEvent before;
    private AnimatorEvent after;

    public CanScale(Node node, double speed) {
    }

    public CanScale(Node node, double speed, AnimatorEvent before, AnimatorEvent after) {
    }

    @Override
    public void animateScaleBy(double alphaW, double alphaH) {
    }

    @Override
    public void animateScaleTo(double w, double h) {
    }
}