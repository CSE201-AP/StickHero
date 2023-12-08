package com.example.stickhero.sprite;


import com.example.stickhero.Callback;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;

public interface MovementAnimator {
    void moveBy(Point2D offset);
    void moveTo(Point2D target);
    void setAfterHandler(EventHandler<ActionEvent> handler);
    void setBeforeCallback(Callback before);
}

