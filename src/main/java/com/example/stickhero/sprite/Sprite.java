package com.example.stickhero.sprite;

public interface Sprite {
    public MovementAnimator getMovementAnimator();
    public RotationAnimator getRotationAnimator();
    public ScaleAnimator getScaleAnimator();
    public void setMovementAnimator(MovementAnimator movementAnimator);
    public void setRotationAnimator(RotationAnimator rotationAnimator);
    public void setScaleAnimator(ScaleAnimator scaleAnimator);
}
