package com.example.stickhero.sprite;


import com.example.stickhero.Callback;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface RotationAnimator {
    void rotateTo(double angle);
    void rotateTo(double angle, double pivotX, double pivotY);
    void rotateBy(double theta);
    void rotateBy(double theta, double pivotX, double pivotY);
    void interrupt();

    void setAfterHandler(EventHandler<ActionEvent> handler);
    void setBeforeCallback(Callback before);
}

