package com.example.stickhero.sprite;

import com.example.stickhero.spritesheet.SpriteSheet;
import com.example.stickhero.spritesheet.SpriteSheetGroup;
import javafx.scene.image.Image;

import java.util.Map;

public class ImageSprite extends SpriteSheet implements Sprite {
    private MovementAnimator movementAnimator;
    private ScaleAnimator scaleAnimator;
    private RotationAnimator rotationAnimator;

    public ImageSprite(Image image, Map<String, SpriteSheetGroup> groups) {
        super(image, groups);
        if (groups.containsKey("movement")) {
            this.movementAnimator = new CanMove(this, groups.get("movement").speed);
            this.movementAnimator.getBeforeCallbacks().add(() -> this.animate("movement"));
            this.movementAnimator.getAfterHandlers().add(e-> this.stopAnimations());
        } else {
            this.movementAnimator = new CannotMove(this);
        }
        if (groups.containsKey("rotation")) {
            this.rotationAnimator = new CanRotate(this, groups.get("rotation").speed);
            this.rotationAnimator.getBeforeCallbacks().add(() -> this.animate("rotation"));
            this.rotationAnimator.getAfterHandlers().add(e-> this.stopAnimations());
        } else {
            this.rotationAnimator = new CannotRotate(this);
        }
        this.scaleAnimator = new CannotScale(this);
    }
    @Override
    public MovementAnimator getMovementAnimator() {
        return movementAnimator;
    }

    @Override
    public void setMovementAnimator(MovementAnimator movementAnimator) {
        this.movementAnimator = movementAnimator;
    }

    @Override
    public RotationAnimator getRotationAnimator() {
        return rotationAnimator;
    }

    @Override
    public void setRotationAnimator(RotationAnimator rotationAnimator) {
        this.rotationAnimator = rotationAnimator;
    }

    @Override
    public ScaleAnimator getScaleAnimator() {
        return scaleAnimator;
    }

    @Override
    public void setScaleAnimator(ScaleAnimator scaleAnimator) {
        this.scaleAnimator = scaleAnimator;
    }

}
