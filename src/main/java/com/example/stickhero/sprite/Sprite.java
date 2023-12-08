package com.example.stickhero.sprite;

import javafx.scene.Node;

public class Sprite {
    protected Node node;
    private MovementAnimator movementAnimator;
    private RotationAnimator rotationAnimator;
    private ScaleAnimator scaleAnimator;

    public Sprite(Node node, MovementAnimator movementAnimator, RotationAnimator rotationAnimator, ScaleAnimator scaleAnimator) {
        this.node = node;
        this.movementAnimator = movementAnimator;
        this.rotationAnimator = rotationAnimator;
        this.scaleAnimator = scaleAnimator;
    }

    public MovementAnimator getMovementAnimator(){
        return movementAnimator;
    }

    public RotationAnimator getRotationAnimator(){
        return rotationAnimator;
    }

    public ScaleAnimator getScaleAnimator(){
        return scaleAnimator;
    }

    public void setMovementAnimator(MovementAnimator movementAnimator){
        this.movementAnimator = movementAnimator;
    }

    public void setRotationAnimator(RotationAnimator rotationAnimator){
        this.rotationAnimator = rotationAnimator;
    }

    public void setScaleAnimator(ScaleAnimator scaleAnimator){
        this.scaleAnimator = scaleAnimator;
    }
}
