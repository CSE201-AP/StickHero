package com.example.stickhero.sprite;


import com.example.stickhero.Callback;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface ScaleAnimator {
    void scaleBy(double alphaW, double alphaH);
    void scaleBy(double alphaW, double alphaH, double pivotX, double pivotY);
    void scaleTo(double w, double h);
    void scaleTo(double w, double h, double pivotX, double pivotY);
    void interrupt();
    void setAfterHandler(EventHandler<ActionEvent> handler);
    void setBeforeCallback(Callback before);
}

