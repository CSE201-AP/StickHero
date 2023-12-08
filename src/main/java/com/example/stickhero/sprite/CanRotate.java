package com.example.stickhero.sprite;

import com.example.stickhero.Callback;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class CanRotate implements RotationAnimator {
    private final double speedMs;
    private final Node node;
    private Callback before;
    private EventHandler<ActionEvent> after;
    private Interpolator interpolator = Interpolator.LINEAR;

    public CanRotate(Node node, double speedMs) {
        this.node = node;
        this.speedMs = speedMs;
        this.before = () -> {/* Do nothing */};
        this.after = (ActionEvent e) -> {/* Do nothing */};
    }

    public void setBeforeCallback(Callback before) {
        this.before = before;
    }

    public void setAfterHandler(EventHandler<ActionEvent> after) {
        this.after = after;
    }

    public void setInterpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
    }

    private void setPivot(double pivotX, double pivotY) {
        node.getTransforms().add(new Translate(pivotX+node.getTranslateX(), pivotY+node.getTranslateY()));
        node.setTranslateX(-pivotX);
        node.setTranslateY(-pivotY);
    }

    @Override
    public void rotateTo(double angle) {
        rotateTo(angle, 0, 0);  // (0, 0) is around center
    }

    @Override
    public void rotateTo(double angle, double pivotX, double pivotY) {
        before.function();
        setPivot(pivotX, pivotY);
        RotateTransition transition = new RotateTransition(
                new Duration((angle - node.getRotate())/speedMs),
                node
        );
        transition.setToAngle(angle);
        transition.setInterpolator(interpolator);
        transition.setOnFinished(this.after);
        transition.play();
    }

    @Override
    public void rotateBy(double theta) {
        double angle = node.getRotate() + theta;
        rotateTo(angle);
    }

    @Override
    public void rotateBy(double theta, double pivotX, double pivotY) {
        double angle = node.getRotate() + theta;
        rotateTo(angle, pivotX, pivotY);
    }
}
