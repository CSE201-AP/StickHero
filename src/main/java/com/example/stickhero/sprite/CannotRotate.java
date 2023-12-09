package com.example.stickhero.sprite;

import com.example.stickhero.Callback;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

import java.util.List;

public class CannotRotate implements RotationAnimator {
    private final Node node;

    public CannotRotate(Node node) {
        this.node = node;
    }

    @Override
    public void rotateTo(double angle) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Can't rotate object " + node.toString() + " using CannotRotate strategy");
    }

    @Override
    public void rotateTo(double angle, double pivotX, double pivotY) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Can't rotate object " + node.toString() + " using CannotRotate strategy");
    }

    @Override
    public void rotateBy(double theta) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Can't rotate object " + node.toString() + " using CannotRotate strategy");
    }

    @Override
    public void rotateBy(double theta, double pivotX, double pivotY) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Can't rotate object " + node.toString() + " using CannotRotate strategy");
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
    public void setSpeedMs(double speedMs) {

    }

    @Override
    public void interrupt() {

    }

    @Override
    public Animation.Status getStatus() {
        return null;
    }
}
