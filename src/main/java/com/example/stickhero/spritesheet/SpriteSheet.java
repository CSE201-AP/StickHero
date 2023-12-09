package com.example.stickhero.spritesheet;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.ImageView;

public class SpriteSheet extends ImageView {
    private final Map<String, SpriteSheetAnimationTimer> animationTimers = new HashMap<>();

    public SpriteSheet(Image image, Map<String, SpriteSheetGroup> groups) {
        this.setImage(image);
        this.setViewport(groups.get("default").sprites.get(0));
        groups.forEach((name, group) -> {
            animationTimers.put(
                name,
                new SpriteSheetAnimationTimer(this, group.sprites, group.duration)
            );
        });
    }

    public void animate(String name) {
        if (animationTimers.get(name) == null) return;
        stopAnimations();
        animationTimers.get(name).start();
    }

    public void stopAnimations() {
        for (SpriteSheetAnimationTimer animationTimer: animationTimers.values()) {
            animationTimer.stop();
        }
    }
}
