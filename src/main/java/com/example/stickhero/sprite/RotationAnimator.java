package com.example.stickhero.sprite;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface RotationAnimator {
    void rotateTo(double angle);
    void rotateTo(double angle, double pivotX, double pivotY);
    void rotateBy(double theta);
    void rotateBy(double theta, double pivotX, double pivotY);
    void setAfterHandler(EventHandler<ActionEvent> handler);
    void setBeforeCallback(Callback before);
}

