package com.example.stickhero.sprite;

import com.example.stickhero.Callback;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;

import java.util.List;

public class CannotMove implements MovementAnimator {
    private final Node node;

    public CannotMove(Node node) {
        this.node = node;
    }

    @Override
    public void moveBy(Point2D offset) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Can't move object " + node.toString() + " using CannotMove strategy");
    }

    @Override
    public List<EventHandler<ActionEvent>> getAfterHandlers() {
        return null;
    }

    @Override
    public List<Callback> getBeforeCallbacks() {
        return null;
    }

    @Override
    public void moveTo(Point2D target) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Can't move object " + node.toString() + " using CannotMove strategy");
    }

    @Override
    public void setSpeedMs(double speedMs) {

    }

    @Override
    public void interrupt() {

    }

    @Override
    public Animation.Status getStatus() {
        return null;
    }

    @Override
    public void resume() {

    }
}
