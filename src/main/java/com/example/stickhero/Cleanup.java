package com.example.stickhero;

import com.example.stickhero.environment.Foreground;
import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

public class Cleanup extends ScheduledService<Void> {
    private final Foreground foreground;

    public Cleanup(Foreground foreground){
        this.foreground = foreground;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<>() {
            @Override
            protected Void call() {
                Platform.runLater(() -> {
                    if (foreground == null){
                        return;
                    }
<<<<<<< HEAD
                    foreground.getChildren().removeIf(node -> {
                        return node.getBoundsInParent().getMaxX() + node.getTranslateX() <= foreground.sceneToLocal(0, 0).getX();
=======
                    System.out.println("Before:" + foreground.getChildren().size());
                    foreground.getChildren().removeIf((node) -> {
                        boolean hidden = node.getBoundsInParent().getMaxX() + node.getTranslateX() <= foreground.sceneToLocal(0, 0).getX();
                        if (hidden) {
                            System.out.println(node + " is hidden");
                        }
                        return hidden;
>>>>>>> 587fedd3f378e1a097d09d60a4d1734c5d600b3f
                    });
                });
                return null;
            }
        };
    }
}