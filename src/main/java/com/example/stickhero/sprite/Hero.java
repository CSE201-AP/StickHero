package com.example.stickhero.sprite;

import com.example.stickhero.Callback;
import com.example.stickhero.CollisionTimer;
import com.example.stickhero.spritesheet.SpriteSheetGroup;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.transform.Translate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Hero extends ImageSprite {
    public static final double SPEED = 0.2;
    public static final double FALL_SPEED = 0.5;
    public static final double WIDTH = 50;
    public Map<Node, Callback> collisionCallbacks = new HashMap<>();
    private final CollisionTimer collisionTimer = new CollisionTimer(this, collisionCallbacks);
    private Stick stick;
    private final IntegerProperty cherriesProperty = new SimpleIntegerProperty();
    private final IntegerProperty scoreProperty = new SimpleIntegerProperty();
    private boolean dying = false;

    public Hero(Image image, Map<String, SpriteSheetGroup> groups) {
        super(image, groups);
        collisionTimer.start();
    }

    public void flip() {
        if (getTranslateY() == 0) {
            double height = getBoundsInLocal().getHeight();
            getTransforms().add(new Translate(0, -height / 2));
            setTranslateX(0);
            setTranslateY(height / 2);
        }
        setScaleY(-getScaleY());
    }

    public Stick getStick() {
        return stick;
    }

    public void setStick(Stick stick) {
        this.stick = stick;
    }

    public void addCherry() {
        cherriesProperty.set(cherriesProperty.get()+1);
        System.out.println("Added cherry");
    }

    public void increaseScore(int delta) {
        scoreProperty.set(scoreProperty.get() + delta);
    }

    public int getScore() {
        return scoreProperty.get();
    }

    public int getCherries() {
        return cherriesProperty.get();
    }

    public IntegerProperty cherriesProperty() {
        return cherriesProperty;
    }

    public IntegerProperty scoreProperty() {
        return scoreProperty;
    }

    public void setDying(boolean missed) {
        dying = missed;
    }

    public boolean isDying() {
        return dying;
    }
}