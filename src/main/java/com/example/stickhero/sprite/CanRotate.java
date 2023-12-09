package com.example.stickhero.sprite;

import com.example.stickhero.Callback;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class CanRotate implements RotationAnimator {
    private double speedMs;
    private final Node node;
    private final List<Callback> beforeCallbacks = new ArrayList<>();
    private final List<EventHandler<ActionEvent>> afterHandlers = new ArrayList<>();
    private Interpolator interpolator = Interpolator.LINEAR;
    private RotateTransition transition;

    public CanRotate(Node node, double speedMs) {
        this.node = node;
        this.speedMs = speedMs;
    }

    public void setSpeedMs(double speedMs) {
        this.speedMs = speedMs;
    }

    @Override
    public List<EventHandler<ActionEvent>> getAfterHandlers() {
        return afterHandlers;
    }

    @Override
    public List<Callback> getBeforeCallbacks() {
        return beforeCallbacks;
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
        for (Callback before : beforeCallbacks) {
            before.function();
        }
        setPivot(pivotX, pivotY);
        transition = new RotateTransition(
                new Duration((angle - node.getRotate())/speedMs),
                node
        );
        transition.setToAngle(angle);
        transition.setInterpolator(interpolator);
        transition.setOnFinished((e) -> {
            List.copyOf(afterHandlers).forEach((handler) -> handler.handle(e));
        });
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

    @Override
    public void interrupt() {
        if (transition != null && transition.getStatus() == ScaleTransition.Status.RUNNING) {
            transition.stop();
        }
    }

    @Override
    public Animation.Status getStatus() {
        if (transition != null) {
            return transition.getStatus();
        }
        return null;
    }

    @Override
    public void resume() {
        if (transition != null) {
            transition.play();
        }
    }
}
