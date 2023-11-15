package com.example.stickhero.structure;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class SpriteSheet extends ImageView {
    private SpriteSheetAnimationTimer movementAnimationTimer;
    private SpriteSheetAnimationTimer rotationAnimationTimer;

    public SpriteSheet(Image image, List<Rectangle2D> movementSprites, long movementDuration, List<Rectangle2D> rotationSprites, long rotationDuration) {
    }

    public SpriteSheet(String url, List<Rectangle2D> movementSprites, long movementDuration, List<Rectangle2D> rotationSprites, long rotationDuration) {
    }

    public void animateMovement() {
    }

    public void animateRotation() {
    }

    public void stopAnimations() {
    }
}