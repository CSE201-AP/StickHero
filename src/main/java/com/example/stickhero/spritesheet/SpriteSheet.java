package com.example.stickhero.spritesheet;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class SpriteSheet extends ImageView {
    private final SpriteSheetAnimationTimer movementAnimationTimer;
    private final SpriteSheetAnimationTimer rotationAnimationTimer;

    public SpriteSheet(Image image, List<Rectangle2D> movementSprites, List<Rectangle2D> rotationSprites, long movementDuration, long rotationDuration) {
        this.setImage(image);
        this.setViewport(movementSprites.get(0));
        movementAnimationTimer = new SpriteSheetAnimationTimer(this, movementSprites, movementDuration);
        rotationAnimationTimer = new SpriteSheetAnimationTimer(this, rotationSprites, rotationDuration);
    }

    public void animateMovement() {
        stopAnimations();
        movementAnimationTimer.start();
    }

    public void animateRotation() {
        stopAnimations();
        rotationAnimationTimer.start();
    }

    public void stopAnimations() {
        movementAnimationTimer.stop();
        rotationAnimationTimer.stop();
    }
}
