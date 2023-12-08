package com.example.stickhero;

import com.example.stickhero.environment.Background;
import com.example.stickhero.environment.BackgroundImage;
import com.example.stickhero.environment.Foreground;
import com.example.stickhero.sprite.Building;
import com.example.stickhero.sprite.CanMove;
import com.example.stickhero.sprite.MovementAnimator;
import com.example.stickhero.sprite.Stick;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.lang.Math.pow;

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

        Background background = new Background();
        for (int i=0; i<2; i++) {
            BackgroundImage backgroundImage = new BackgroundImage(
                    new Image(Objects.requireNonNull(getClass().getResourceAsStream("background" + (i+1) + ".png"))),
                    (double) 10 /(i+1)
            );
            backgroundImage.minHeightProperty().bind(background.heightProperty());
            background.getChildren().add(backgroundImage);
        }

        Foreground foreground = new Foreground(0.5);
        AnchorPane.setBottomAnchor(foreground, 0D);
        for (int i=0; i<4; i++) {
            Building building = new Building();
            foreground.getChildren().add(building);
            building.setLayoutX(100 + i*100);
        }
        Stick stick = new Stick();
        stick.setLayoutX(100);
        stick.setHandler((e) -> {
            foreground.panHorizontal(-stick.getScaleY());
        });
        stick.setFill(Color.YELLOW);
//        stick.setTranslateX(100);
//        stick.setTranslateY(100);
        foreground.getChildren().add(stick);

        anchorPane.setOnMousePressed((e) -> {
            stick.startExtendStick();
        });
        anchorPane.setOnMouseReleased((e) -> {
            stick.stopExtendStick();
        });

        anchorPane.getChildren().addAll(background, foreground);
        background.maxHeightProperty().bind(anchorPane.heightProperty());
        anchorPane.maxHeightProperty().bind(stage.heightProperty());
        anchorPane.minHeightProperty().bind(stage.heightProperty());
        stage.setScene(new Scene(anchorPane));
        stage.setWidth(335);
        stage.setHeight(600);
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