package com.example.stickhero;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import com.example.stickhero.Callback;

import java.util.Map;

public class CollisionTimer extends AnimationTimer {
//    public CollisionTimer(Node node, Map<Class, Callback>) {
//        super();
//    }

    @Override
    public void handle(long l) {

    }

    interface Callback {
        void function();
    }
}
