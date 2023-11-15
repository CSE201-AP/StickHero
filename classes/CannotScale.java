package com.example.stickhero.structure;

import javafx.scene.Node;

public class CannotScale implements ScaleAnimator {
    @Override
    public void animateScaleBy(double alphaW, double alphaH) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("CannotScale does not support scaling");
    }

    @Override
    public void animateScaleTo(double w, double h) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("CannotScale does not support scaling");
    }
}