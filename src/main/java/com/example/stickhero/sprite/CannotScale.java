package com.example.stickhero.sprite;

import com.example.stickhero.Callback;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

import java.util.List;

public class CannotScale implements ScaleAnimator {
    private final Node node;

    public CannotScale(Node node) {
        this.node = node;
    }

    @Override
    public void scaleBy(double alphaW, double alphaH) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Can't scale object " + node.toString() + " using CannotScale strategy");
    }

    @Override
    public void scaleTo(double w, double h) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Can't scale object " + node.toString() + " using CannotScale strategy");
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
    public void scaleBy(double alphaW, double alphaH, double pivotX, double pivotY) {
        throw new UnsupportedOperationException("Can't scale object " + node.toString() + " using CannotScale strategy");
    }

    @Override
    public void scaleTo(double w, double h, double pivotX, double pivotY) {
        throw new UnsupportedOperationException("Can't scale object " + node.toString() + " using CannotScale strategy");
    }

    @Override
    public void setSpeedMs(double speedMs) {
        throw new UnsupportedOperationException("Can't scale object " + node.toString() + " using CannotScale strategy");
    }

    @Override
    public void interrupt() {

    }

    @Override
    public Animation.Status getStatus() {
        return null;
    }
}
