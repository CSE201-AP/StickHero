package com.example.stickhero.sprite;

import com.example.stickhero.Callback;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

public class CannotScale implements ScaleAnimator {
    private final Node node;
    public CannotScale(Node node) {
        this.node = node;
    }
    @Override
    public void scaleBy(double alphaW, double alphaH) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Can't scale object "+ node.toString() +" using CannotScale strategy");
    }

    @Override
    public void scaleTo(double w, double h) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Can't scale object "+ node.toString() +" using CannotScale strategy");
    }

    @Override
    public void setAfterHandler(EventHandler<ActionEvent> handler) {

    }

    @Override
    public void setBeforeCallback(Callback before) {

    }
}
