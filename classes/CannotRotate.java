package com.example.stickhero.structure;

public class CannotRotate implements RotationAnimator {
    @Override
    public void rotateTo(double angle) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("CannotRotate does not support rotation");
    }

    @Override
    public void rotateTo(double angle, double pivotX, double pivotY) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("CannotRotate does not support rotation");
    }

    @Override
    public void rotateBy(double theta) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("CannotRotate does not support rotation");
    }

    @Override
    public void rotateBy(double theta, double pivotX, double pivotY) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("CannotRotate does not support rotation");
    }
}