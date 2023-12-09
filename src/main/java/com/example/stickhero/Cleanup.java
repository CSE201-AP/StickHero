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
                    if (foreground == null) {
                        return;
                    }
                    System.out.println("Before:" + foreground.getChildren().size());
                    foreground.getChildren().removeIf((node) -> {
                        System.out.println("node.getBoundsInLocal().getMaxX() = " + node.getBoundsInLocal().getMaxX());
                        System.out.println("foreground.sceneToLocal(0, 0).getX() = " + foreground.sceneToLocal(0, 0).getX());
                        if (node.getBoundsInLocal().getMaxX() <= foreground.sceneToLocal(0, 0).getX()) {
                            System.out.println(node + " is hidden");
                        }
                        // try kar run karne ki
                        return node.getBoundsInLocal().getMaxX() <= foreground.sceneToLocal(0, 0).getX();
                    });
                    System.out.println("After:" + foreground.getChildren().size());
                });
                return null;
            }
        };
    }
}