package com.example.stickhero.sprite;

import com.example.stickhero.Callback;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

public class CannotRotate implements RotationAnimator {
    private final Node node;
    public CannotRotate(Node node) {
        this.node = node;
    }

    @Override
    public void rotateTo(double angle) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Can't rotate object "+ node.toString() +" using CannotRotate strategy");
    }

    @Override
    public void rotateTo(double angle, double pivotX, double pivotY) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Can't rotate object "+ node.toString() +" using CannotRotate strategy");
    }

    @Override
    public void rotateBy(double theta) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Can't rotate object "+ node.toString() +" using CannotRotate strategy");
    }

    @Override
    public void rotateBy(double theta, double pivotX, double pivotY) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Can't rotate object "+ node.toString() +" using CannotRotate strategy");
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
