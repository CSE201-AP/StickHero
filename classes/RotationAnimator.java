package com.example.stickhero.structure;

public interface RotationAnimator {
    void rotateTo(double angle);

    void rotateTo(double angle, double pivotX, double pivotY);

    void rotateBy(double theta);

    void rotateBy(double theta, double pivotX, double pivotY);
}