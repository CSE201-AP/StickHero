package com.example.stickhero.environment;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

public class Background extends StackPane implements Pannable {
    public Background() {
    }

    @Override
    public void panHorizontal(double offset) {
        for (Node node : getChildren()) {
            if (node.getClass() == BackgroundImage.class) {
                ((BackgroundImage) node).panHorizontal(offset);
            }
        }
    }

    @Override
    public void panHorizontalRelative(double offset) {
        for (Node node : getChildren()) {
            if (node.getClass() == BackgroundImage.class) {
                ((BackgroundImage) node).panHorizontalRelative(offset);
            }
        }
    }
}
