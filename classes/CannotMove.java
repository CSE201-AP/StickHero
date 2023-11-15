package com.example.stickhero.structure;

import javafx.geometry.Point2D;

public class CannotMove implements MovementAnimator {
    @Override
    public void moveBy(Point2D offset) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("CannotMove does not support movement");
    }

    @Override
    public void moveTo(Point2D target) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("CannotMove does not support movement");
    }
}