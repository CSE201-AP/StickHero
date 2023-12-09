package com.example.stickhero.sprite;


import com.example.stickhero.Callback;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.List;

public interface ScaleAnimator {
    void scaleBy(double alphaW, double alphaH);

    void scaleBy(double alphaW, double alphaH, double pivotX, double pivotY);

    void scaleTo(double w, double h);

    void scaleTo(double w, double h, double pivotX, double pivotY);

    void setSpeedMs(double speedMs);

    void interrupt();

    List<EventHandler<ActionEvent>> getAfterHandlers();

    List<Callback> getBeforeCallbacks();

    Animation.Status getStatus();
}

