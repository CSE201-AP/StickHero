package com.example.stickhero.sprite;

import com.example.stickhero.Callback;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class CanScale implements ScaleAnimator {
    private final double speedMs;
    private ScaleTransition transition;
    private final Node node;
    private Callback before;
    private EventHandler<ActionEvent> after;
    private Interpolator interpolator = Interpolator.LINEAR;

    public CanScale(Node node, double speedMs) {
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
        before.function();
        setPivot(pivotX, pivotY);
        double currentW = node.getBoundsInLocal().getWidth(), currentH = node.getBoundsInLocal().getHeight();
        transition = new ScaleTransition(
                new Duration(Math.max(Math.abs(w-currentW), Math.abs(h-currentH))/speedMs),
                node
        );
        transition.setToX(w/currentW);
        transition.setToY(h/currentH);
        transition.setInterpolator(interpolator);
        transition.setOnFinished(after);
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
}
