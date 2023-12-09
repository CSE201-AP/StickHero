package com.example.stickhero.sprite;


import com.example.stickhero.Callback;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;

import java.util.List;

public interface MovementAnimator {
    void moveBy(Point2D offset);

    void moveTo(Point2D target);

    void setSpeedMs(double speedMs);

    void interrupt();

    List<EventHandler<ActionEvent>> getAfterHandlers();

    List<Callback> getBeforeCallbacks();

    Animation.Status getStatus();

    void resume();
}

