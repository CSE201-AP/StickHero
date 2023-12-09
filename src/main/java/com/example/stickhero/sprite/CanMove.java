package com.example.stickhero.sprite;

import com.example.stickhero.Callback;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class CanMove implements MovementAnimator {
    private double speedMs;
    private final Node node;
    private final List<Callback> beforeCallbacks = new ArrayList<>();
    private final List<EventHandler<ActionEvent>> afterHandlers = new ArrayList<>();
    private Interpolator interpolator = Interpolator.LINEAR;
    TranslateTransition transition;

    public CanMove(Node node, double speedMs) {
        this.node = node;
        this.speedMs = speedMs;
    }

    public void setSpeedMs(double speedMs) {
        this.speedMs = speedMs;
    }

    @Override
    public List<EventHandler<ActionEvent>> getAfterHandlers() {
        return afterHandlers;
    }

    @Override
    public List<Callback> getBeforeCallbacks() {
        return beforeCallbacks;
    }

    public void setInterpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
    }

    @Override
    public void moveBy(Point2D offset) {
        Point2D target = new Point2D(node.getTranslateX() + offset.getX(), node.getTranslateY() + offset.getY());
        moveTo(target);
    }

    @Override
    public void moveTo(Point2D target) {
        for (Callback before : beforeCallbacks) {
            before.function();
        }
        transition = new TranslateTransition(
                new Duration(
                        Math.max(
                                Math.abs(target.getX() - node.getTranslateX()),
                                Math.abs(target.getY() - node.getTranslateY())
                        ) / speedMs
                ),
                node
        );
        if (node.getTranslateX() != target.getX()) {
            transition.setToX(target.getX());
        }
        if (node.getTranslateY() != target.getY()) {
            transition.setToY(target.getY());
        }
        transition.setInterpolator(interpolator);
        transition.setOnFinished((e) -> {
            List.copyOf(afterHandlers).forEach((handler) -> handler.handle(e));
        });
        transition.play();
    }

    @Override
    public void interrupt() {
        if (transition != null) {
            transition.pause();
        }
    }

    @Override
    public Animation.Status getStatus() {
        if (transition != null) {
            return transition.getStatus();
        }
        return null;
    }
}
