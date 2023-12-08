package com.example.stickhero.sprite;


import com.example.stickhero.Callback;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface ScaleAnimator {
    void scaleBy(double alphaW, double alphaH);
    void scaleTo(double w, double h);
    void setAfterHandler(EventHandler<ActionEvent> handler);
    void setBeforeCallback(Callback before);
}

