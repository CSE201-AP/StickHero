package com.example.stickhero.environment;

import com.example.stickhero.sprite.CanMove;
import com.example.stickhero.sprite.MovementAnimator;
import com.example.stickhero.sprite.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Foreground extends AnchorPane implements Pannable {
    MovementAnimator movementAnimator;

    public Foreground(double speed) {
        super();
        movementAnimator = new CanMove(this, speed);
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
