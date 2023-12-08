package com.example.stickhero.sprite;

import com.example.stickhero.Callback;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.util.Duration;

public class CanMove implements MovementAnimator {
    private final double speedMs;
    private final Node node;
    private Callback before;
    private EventHandler<ActionEvent> after;
    private Interpolator interpolator = Interpolator.LINEAR;

    public CanMove(Node node, double speedMs) {
        this.node = node;
        this.speedMs = speedMs;
        this.before = () -> {/* Do nothing */};
        this.after = (ActionEvent e) -> {/* Do nothing */};
    }

    public void setBeforeCallback(Callback before) {
        this.before = before;
    }

    public void setAfterHandler(EventHandler<ActionEvent> after) {
        this.after = after;
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
        before.function();
        TranslateTransition transition = new TranslateTransition(
                new Duration(speedMs * Math.max(Math.abs(target.getX() - node.getTranslateX()), Math.abs(target.getY() - node.getTranslateY()))),
                node
        );
        transition.setToX(target.getX());
        transition.setToY(target.getY());
        transition.setInterpolator(interpolator);
        transition.setOnFinished(after);
        transition.play();
    }
}
