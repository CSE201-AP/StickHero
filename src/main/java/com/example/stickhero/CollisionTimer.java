package com.example.stickhero;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import com.example.stickhero.Callback;

import java.util.Map;

public class CollisionTimer extends AnimationTimer {
    private final Map<Node, Callback> callbacks;
    private final Node node;
    public CollisionTimer(Node node, Map<Node, Callback> callbacks) {
        super();
        this.node = node;
        this.callbacks = callbacks;
    }

    @Override
    public void handle(long l) {
        callbacks.forEach((Node otherNode, Callback callback) -> {
            Bounds otherNodeInScene = otherNode.localToScene(otherNode.getBoundsInLocal());
            if (node.intersects(node.sceneToLocal(otherNodeInScene))) {
                System.out.println(node + " intersecting with " + otherNode);
                callback.function();
            }
        });
    }
}
