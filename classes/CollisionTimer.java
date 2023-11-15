package com.example.stickhero.structure;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;

import java.util.Map;

public class CollisionTimer extends AnimationTimer {
    private final Node node;
    private final Map<Node, AnimatorEvent> callbacks;
    private boolean active = true;

    public CollisionTimer(Node node, Map<Node, AnimatorEvent> callbacks) {

    }

    public void setActive(boolean active) {

    }

    @Override
    public void handle(long now) {

    }

}