package com.example.stickhero;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import com.example.stickhero.Callback;

import java.util.HashMap;
import java.util.Map;

/**
 * Checks for collisions every frame between the given node and all "obstacle"
 * nodes provided via callbacks.
 * If the source node is also present in the provided obstacles, the
 * corresponding callback is invoked when there are no obstacle collisions.
 */
public class CollisionTimer extends AnimationTimer {
    private final Map<Node, Callback> callbacks;
    private boolean colliding = false;
    private final Node node;

    public CollisionTimer(Node node, Map<Node, Callback> callbacks) {
        super();
        this.node = node;
        this.callbacks = callbacks;
    }

    public Map<Node, Callback> getCallbacks() {
        return callbacks;
    }

    @Override
    public void handle(long l) {
        colliding = false;
        (new HashMap<>(callbacks)).forEach((Node obstacle, Callback callback) -> {
            if (obstacle == node) return;
            Bounds otherNodeInScene = obstacle.localToScene(obstacle.getBoundsInLocal());
            if (node.intersects(node.sceneToLocal(otherNodeInScene))) {
                this.colliding = true;
                callback.function();
            }
        });
        if (!colliding && callbacks.containsKey(node)) {
            callbacks.get(node).function();
        }
    }
}
