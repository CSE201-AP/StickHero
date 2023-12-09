package com.example.stickhero.sprite;

import com.example.stickhero.environment.Foreground;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Building extends StackPane implements Sprite {
    public static final double MINIMUM_DISTANCE = 80;
    private static final Random random = new Random();
    private static final double MIN_THICKNESS = 10;
    private static final double MAX_THICKNESS = 70;
    public static final double HEIGHT = 200;
    private static final double PERFECT_BLOCK_WIDTH = 6;
    private static final double PERFECT_BLOCK_HEIGHT = 4;
    private final Rectangle rectangle;
    private final Rectangle perfectBlock;
    private MovementAnimator movementAnimator = new CanMove(this, Foreground.SPEED);
    private RotationAnimator rotationAnimator = new CannotRotate(this);
    private ScaleAnimator scaleAnimator = new CannotScale(this);

    public Building() {
        this(MIN_THICKNESS, MAX_THICKNESS);
    }

    public Building(double thickness) {
        rectangle = new Rectangle(thickness, HEIGHT);
        perfectBlock = new Rectangle(PERFECT_BLOCK_WIDTH, PERFECT_BLOCK_HEIGHT);
        perfectBlock.setFill(Color.RED);
//        perfectBlock.setTranslateX(building.getWidth() / 2 - perfectBlockWidth / 2);
        perfectBlock.setTranslateY(-HEIGHT / 2 + PERFECT_BLOCK_HEIGHT / 2);
        getChildren().addAll(rectangle, perfectBlock);
    }

    public Building(double minThickness, double maxThickness) {
        this(random.nextDouble(minThickness, maxThickness));
    }

    public Rectangle getPerfectBlock() {
        return perfectBlock;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    @Override
    public MovementAnimator getMovementAnimator() {
        return movementAnimator;
    }

    @Override
    public void setMovementAnimator(MovementAnimator movementAnimator) {
        this.movementAnimator = movementAnimator;
    }

    @Override
    public RotationAnimator getRotationAnimator() {
        return rotationAnimator;
    }

    @Override
    public void setRotationAnimator(RotationAnimator rotationAnimator) {
        this.rotationAnimator = rotationAnimator;
    }

    @Override
    public ScaleAnimator getScaleAnimator() {
        return scaleAnimator;
    }

    @Override
    public void setScaleAnimator(ScaleAnimator scaleAnimator) {
        this.scaleAnimator = scaleAnimator;
    }
}