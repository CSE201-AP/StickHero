package com.example.stickhero.environment;

import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

public class Background extends StackPane implements Pannable {
    private final ArrayList<BackgroundImage> backgroundImages;

    public Background(List<BackgroundImage> backgroundImages) {
        this.backgroundImages = new ArrayList<>(backgroundImages);
    }

    public ArrayList<BackgroundImage> getBackgroundImages() {
        return backgroundImages;
    }

    @Override
    public void panHorizontal(double offset) {
        for (BackgroundImage backgroundImage : backgroundImages) {
            backgroundImage.panHorizontal(offset);
        }
    }
}
