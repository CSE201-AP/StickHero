package com.example.stickhero.environment;

import com.example.stickhero.sprite.CanMove;
import com.example.stickhero.sprite.MovementAnimator;
import com.example.stickhero.sprite.Sprite;
import javafx.beans.DefaultProperty;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@DefaultProperty("children")
public class Foreground extends AnchorPane implements Pannable {
    public static final double SPEED = 0.5;
    MovementAnimator movementAnimator;

    public Foreground() {
        super();
        movementAnimator = new CanMove(this, SPEED);
    }

    @Override
    public void panHorizontal(double offset) {
        movementAnimator.moveBy(new Point2D(offset, 0));
    }

    @Override
    public void panHorizontalRelative(double offset) {
        panHorizontal(offset);  // No depth in foreground
    }
}
