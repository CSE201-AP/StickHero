package com.example.stickhero.sprite;

import com.example.stickhero.Callback;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;

public class CannotMove implements MovementAnimator {
    private final Node node;
    public CannotMove(Node node) {
        this.node = node;
    }
    @Override
    public void moveBy(Point2D offset) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Can't move object "+ node.toString() +" using CannotMove strategy");
    }

    @Override
    public void moveTo(Point2D target) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Can't move object "+ node.toString() +" using CannotMove strategy");
    }

    @Override
    public void setAfterHandler(EventHandler<ActionEvent> handler) {

    }

    @Override
    public void setBeforeCallback(Callback before) {

    }

    @Override
    public void interrupt() {

    }
}
