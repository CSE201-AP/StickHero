package com.example.stickhero;

import com.example.stickhero.environment.BackgroundImage;
import com.example.stickhero.sprite.CanMove;
import com.example.stickhero.sprite.MovementAnimator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class StickHero extends Application {
    private static final double WIDTH = 335;
    private static final double HEIGHT = 600;
    private static StickHero instance;
    //    private List<Pannable> environment;
    private Stage stage;
//    private Hero hero;

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
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setMaxHeight(250);
        BackgroundImage backgroundImage1 = new BackgroundImage(
                new Image(Objects.requireNonNull(getClass().getResourceAsStream("background.png"))),
                10
        );
        backgroundImage1.setMaxHeight(250);
        BackgroundImage backgroundImage2 = new BackgroundImage(
                new Image(Objects.requireNonNull(getClass().getResourceAsStream("background2.png"))),
                100
        );
        backgroundImage2.setMaxHeight(250);
        anchorPane.getChildren().addAll(backgroundImage1, backgroundImage2);

        backgroundImage1.setOnMouseClicked((MouseEvent e) -> {
            backgroundImage1.panHorizontal(-450);
        });
        backgroundImage2.setOnMouseClicked((MouseEvent e) -> {
            backgroundImage1.panHorizontal(-450);
        });

        stage.setScene(new Scene(anchorPane));
        stage.setWidth(900);
        stage.setHeight(400);
        stage.show();
    }

    public void loadFXMLScene(String resource) {
        FXMLLoader fxmlLoader = new FXMLLoader(StickHero.class.getResource(resource));
        try {
            Scene scene = new Scene(fxmlLoader.load(), StickHero.WIDTH, StickHero.HEIGHT);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.printf("Missing resource: %s\n", resource);
            System.exit(1);
        }
    }

//    public Hero getHero(){
//    }
}