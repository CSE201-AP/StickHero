package com.example.stickhero.sprite;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;
import java.util.Random.*;
import java.util.random.RandomGenerator;

public class Building extends StackPane implements Sprite {
    private static final Random random = new Random();
    private final Rectangle building;
    private final Rectangle perfectBlock;

    private static final double minThickness = 10;
    private static final double maxThickness = 70;
    private static final double height = 200;
    private static final double perfectBlockWidth = 4;
    private static final double perfectBlockHeight = 3;

    private MovementAnimator movementAnimator = new CannotMove(this);
    private RotationAnimator rotationAnimator = new CannotRotate(this);
    private ScaleAnimator scaleAnimator = new CannotScale(this);

    public Building(){
        building = new Rectangle(random.nextDouble(minThickness, maxThickness), height);
        perfectBlock = new Rectangle(perfectBlockWidth, perfectBlockHeight);
        perfectBlock.setFill(Color.RED);
//        perfectBlock.setTranslateX(building.getWidth() / 2 - perfectBlockWidth / 2);
        perfectBlock.setTranslateY(-height/2 + perfectBlockHeight/2);
        getChildren().addAll(building, perfectBlock);
    }

    @Override
    public MovementAnimator getMovementAnimator() {
        return movementAnimator;
    }

    @Override
    public RotationAnimator getRotationAnimator() {
        return rotationAnimator;
    }

    @Override
    public ScaleAnimator getScaleAnimator() {
        return scaleAnimator;
    }

    @Override
    public void setMovementAnimator(MovementAnimator movementAnimator) {
        this.movementAnimator = movementAnimator;
    }

    @Override
    public void setRotationAnimator(RotationAnimator rotationAnimator) {
        this.rotationAnimator = rotationAnimator;
    }

    @Override
    public void setScaleAnimator(ScaleAnimator scaleAnimator) {
       this.scaleAnimator = scaleAnimator;
    }
}