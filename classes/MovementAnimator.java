package com.example.stickhero.structure;

import javafx.geometry.Point2D;

public interface MovementAnimator {
    void moveBy(Point2D offset);

    void moveTo(Point2D target);
}