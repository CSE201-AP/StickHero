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

        for (int i=1; i<3; i++) {
            BackgroundImage backgroundImage = new BackgroundImage(
                    new Image(Objects.requireNonNull(getClass().getResourceAsStream("background" + i + ".png"))),
                    pow(10, i)
            );
            AnchorPane.setTopAnchor(backgroundImage, 0D);
            AnchorPane.setBottomAnchor(backgroundImage, 0D);
            backgroundImage.setOnMouseClicked((MouseEvent e) -> {
                backgroundImage.panHorizontal(-450);
            });
            anchorPane.getChildren().add(backgroundImage);
        }

        stage.setScene(new Scene(anchorPane));
        stage.setWidth(400);
        stage.setHeight(271);
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