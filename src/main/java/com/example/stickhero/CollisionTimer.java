package com.example.stickhero;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import com.example.stickhero.Callback;

import java.util.Map;

public class CollisionTimer extends AnimationTimer {
    private final Map<Node, Callback> callbacks;
    private boolean colliding = false;
    private final Node node;
    public CollisionTimer(Node node, Map<Node, Callback> callbacks) {
        super();
        this.node = node;
        this.callbacks = callbacks;
    }

    @Override
    public void handle(long l) {
        colliding = false;
        callbacks.forEach((Node otherNode, Callback callback) -> {
            if (otherNode == node) return;
            Bounds otherNodeInScene = otherNode.localToScene(otherNode.getBoundsInLocal());
            if (node.intersects(node.sceneToLocal(otherNodeInScene))) {
                System.out.println(node + " intersecting with " + otherNode);
                this.colliding = true;
                callback.function();
            }
        });
        if (!colliding && callbacks.containsKey(node)) {
            callbacks.get(node).function();
        }
    }
}
