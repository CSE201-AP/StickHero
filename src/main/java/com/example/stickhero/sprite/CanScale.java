package com.example.stickhero.sprite;

import com.example.stickhero.Callback;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class CanScale implements ScaleAnimator {
    private double speedMs;
    private final Node node;
    private ScaleTransition transition;
    private final List<Callback> beforeCallbacks = new ArrayList<>();
    private final List<EventHandler<ActionEvent>> afterHandlers = new ArrayList<>();
    private Interpolator interpolator = Interpolator.LINEAR;

    public CanScale(Node node, double speedMs) {
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
        node.getTransforms().add(new Translate(pivotX + node.getTranslateX(), pivotY + node.getTranslateY()));
        node.setTranslateX(-pivotX);
        node.setTranslateY(-pivotY);
    }

    @Override
    public void scaleBy(double alphaW, double alphaH) {
        scaleBy(alphaW, alphaH, 0, 0);
    }

    @Override
    public void scaleBy(double alphaW, double alphaH, double pivotX, double pivotY) {
        double w = node.getBoundsInLocal().getWidth() * (1 + alphaW);
        double h = node.getBoundsInLocal().getHeight() * (1 + alphaH);
        scaleTo(w, h, pivotX, pivotY);
    }

    @Override
    public void scaleTo(double w, double h, double pivotX, double pivotY) {
        for (Callback before : beforeCallbacks) {
            before.function();
        }
        setPivot(pivotX, pivotY);
        double currentW = node.getBoundsInLocal().getWidth(), currentH = node.getBoundsInLocal().getHeight();
        transition = new ScaleTransition(new Duration(Math.max(Math.abs(w - currentW), Math.abs(h - currentH)) / speedMs), node);
        double scaleW = w / currentW;
        double scaleH = h / currentH;
        if (Math.abs(node.getScaleX() - scaleW) > 1e-6) {
            transition.setToX(scaleW);
        }
        if (Math.abs(node.getScaleY() - scaleH) > 1e-6) {
            transition.setToY(scaleH);
        }
        transition.setInterpolator(interpolator);
        transition.setOnFinished((e) -> {
            List.copyOf(afterHandlers).forEach((handler) -> handler.handle(e));
        });
        transition.play();
    }

    @Override
    public void scaleTo(double w, double h) {
        scaleTo(w, h, 0, 0);
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
