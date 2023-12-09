package com.example.stickhero;

import com.example.stickhero.environment.Background;
import com.example.stickhero.environment.Foreground;
import com.example.stickhero.sprite.*;
import javafx.application.Application;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class StickHero extends Application {
    private static final double WIDTH = 335;
    private static final double HEIGHT = 600;
    private static StickHero instance;
    private Stage stage;
    private Hero hero;
    private static Foreground foreground;
    private Background background;

    public static void main(String[] args) {
        launch(args);
    }

    public static StickHero getInstance() {
        return instance;
    }

    @Override
    public void init() {
        instance = this;
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("Stick Hero");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/Hero.png"))));

        Cleanup cleanup = new Cleanup();
        cleanup.setPeriod(Duration.seconds(2));
//        cleanup.start();
        loadFXMLScene("fxml/in-game.fxml");
        stage.show();
    }

    public void loadFXMLScene(String resource) {
        FXMLLoader fxmlLoader = new FXMLLoader(StickHero.class.getResource(resource));
        try {
            Scene scene = new Scene(fxmlLoader.load(), StickHero.WIDTH, StickHero.HEIGHT);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.printf("Missing resource: %s\n", resource);
            System.exit(1);
        }
    }

    private class Cleanup extends ScheduledService<Void> {
        @Override
        protected Task<Void> createTask() {
            return new Task<>() {
                @Override
                protected Void call() {
//                    Platform.runLater(() -> {
    //                    System.out.println("Cleaning up")
    //                    foreground.getChildren().removeIf((node) -> {
    //                        return node.getLayoutX() <= -node.getBoundsInLocal().getWidth();
    //                    });
//                    });
                    return null;
                }
            };
        }
    }

    Hero getHero() {
        return hero;
    }

    void setHero(Hero hero) {
        this.hero = hero;
    }

    Foreground getForeground() {
        return foreground;
    }

    void setForeground(Foreground foreground) {
        this.foreground = foreground;
    }

    Background getBackground() {
        return background;
    }

    void setBackground(Background background) {
        this.background = background;
    }
}