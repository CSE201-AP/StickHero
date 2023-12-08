package com.example.stickhero;

import com.example.stickhero.sprite.CanMove;
import com.example.stickhero.sprite.MovementAnimator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

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
        stage.getIcons().add(new Image(getClass().getResourceAsStream("images/Hero.png")));
        AnchorPane anchorPane = new AnchorPane();
//        stackPane.getChildren().add(backgroundImage);

        Rectangle obstacle = new Rectangle(30, 100);
        obstacle.setTranslateX(100);
        obstacle.setFill(Color.RED);

        Rectangle rectangle = new Rectangle(20, 50);
        rectangle.setFill(Color.GREEN);

        CollisionTimer collisionTimer = new CollisionTimer(rectangle, Map.of(
                obstacle, () -> {
                    rectangle.setFill(Color.YELLOW);
                },
                rectangle, () -> {
                    rectangle.setFill(Color.GREEN);
                }
        ));
        collisionTimer.start();

        MovementAnimator movementAnimator = new CanMove(rectangle, 0.1);
        movementAnimator.moveBy(new Point2D(
                300,
                50
        ));

        anchorPane.getChildren().addAll(obstacle, rectangle);

        stage.setScene(new Scene(anchorPane));
        stage.setWidth(500);
        stage.setHeight(300);
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