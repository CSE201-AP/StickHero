package com.example.stickhero.sprite;

import com.example.stickhero.Callback;
import com.example.stickhero.CollisionTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.Rectangle;

import javax.swing.*;

public class Stick extends Rectangle implements Sprite {
    // private Hero hero
    private static final double thickness = 4;
    private static final double MAX_HEIGHT = 600D;
    private CollisionTimer collisionTimer;
    private MovementAnimator movementAnimator = new CannotMove(this);
    private RotationAnimator rotationAnimator = new CanRotate(this, 0.4);
    private ScaleAnimator scaleAnimator = new CanScale(this, 0.2);
    public Stick(EventHandler<ActionEvent> handler){
        this();
        rotationAnimator.setAfterHandler(handler);
    }

    public Stick(){
        super(thickness, 1);
        rotationAnimator.setBeforeCallback(() -> {
            System.out.println("Started rotation");
        });
        scaleAnimator.setBeforeCallback(() -> {
            System.out.println("Started scaling");
        });

    }

    public void setHandler(EventHandler<ActionEvent> handler){
        rotationAnimator.setAfterHandler(handler);
    }
    
    public void startExtendStick() {
        System.out.println("getHeight()*getScaleY() = " + getHeight() * getScaleY());
        scaleAnimator.scaleTo(thickness, Stick.MAX_HEIGHT, 0, -getHeight()/2);
    }

    public void stopExtendStick(){
        scaleAnimator.interrupt();
        topple();
    }

    public void topple(){
        rotationAnimator.rotateBy(90, 0, -getHeight()/2);
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
