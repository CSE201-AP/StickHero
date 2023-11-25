package com.example.stickhero.sprite;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.transform.Rotate;
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
        node.setTranslateX(-pivotX);
        node.setTranslateY(-pivotY);
        Translate temp = new Translate(pivotX, pivotY);
        node.getTransforms().add(temp);
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
                new Duration(speedMs * (angle - node.getRotate())),
                node
        );
        transition.setToAngle(angle);
        System.out.println(Rotate.Z_AXIS);
//        transition.setAxis(new Point3D(0, pivotX, pivotY));
//        node.setRotationAxis(new Point3D(0, 1, 0));
        transition.setInterpolator(interpolator);
        transition.setOnFinished(after);
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
