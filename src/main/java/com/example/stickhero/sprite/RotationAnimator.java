package com.example.stickhero.sprite;


import com.example.stickhero.Callback;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.List;

public interface RotationAnimator {
    void rotateTo(double angle);

    void rotateTo(double angle, double pivotX, double pivotY);

    void rotateBy(double theta);

    void rotateBy(double theta, double pivotX, double pivotY);

    void setSpeedMs(double speedMs);

    void interrupt();

    List<EventHandler<ActionEvent>> getAfterHandlers();

    List<Callback> getBeforeCallbacks();

    Animation.Status getStatus();

    void resume();
}

