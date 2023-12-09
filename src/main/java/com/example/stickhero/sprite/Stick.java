package com.example.stickhero.sprite;

import com.example.stickhero.Callback;
import com.example.stickhero.CollisionTimer;
import com.example.stickhero.Sound;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;

import javax.swing.*;

public class Stick extends Rectangle implements Sprite {
    private static final double thickness = 3;
    private static final double MAX_HEIGHT = 600D;
    private CollisionTimer collisionTimer;
    private MovementAnimator movementAnimator = new CannotMove(this);
    private RotationAnimator rotationAnimator = new CanRotate(this, 0.4);
    private ScaleAnimator scaleAnimator = new CanScale(this, 0.2);
    private static final AudioClip stickExtendSound = Sound.getSound("stick_grow_loop");

    public Stick(EventHandler<ActionEvent> handler){
        this();
        rotationAnimator.getAfterHandlers().add(handler);
    }

    public Stick(){
        super(thickness, 1);

    }

    public void startExtendStick() {
        scaleAnimator.scaleTo(thickness, Stick.MAX_HEIGHT, 0, -getHeight()/2);
        stickExtendSound.setCycleCount(AudioClip.INDEFINITE);
        stickExtendSound.play();
    }

    public void stopExtendStick(){
        scaleAnimator.interrupt();
        stickExtendSound.stop();
        topple();
    }

    public void topple(){
        rotationAnimator.rotateBy(90, 0, -getHeight()/2);
        Sound.getSound("fall").play();
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
