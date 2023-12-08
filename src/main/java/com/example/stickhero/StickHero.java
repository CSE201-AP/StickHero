package com.example.stickhero;

import com.example.stickhero.environment.BackgroundImage;
import com.example.stickhero.sprite.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class StickHero extends Application {
    private static StickHero instance;
    private static final double WIDTH = 335;
    private static final double HEIGHT = 600;

//    private List<Pannable> environment;
    private Stage stage;
//    private Hero hero;

    public static void main(String[] args) {
        launch(args);
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
        StackPane stackPane = new StackPane();
        Image image = new Image(getClass().getResourceAsStream("background.png"));
        BackgroundImage backgroundImage = new BackgroundImage(image, 1);
        backgroundImage.setOnMouseClicked(
                (MouseEvent e) -> {
                    backgroundImage.panHorizontal(e.getButton() == MouseButton.PRIMARY ? -450 : 450);
//                    backgroundImage.setTranslateX(backgroundImage.getTranslateX() - 100);
                }
        );
//        stackPane.getChildren().add(backgroundImage);

//        Rectangle rectangle = new Rectangle();
//        rectangle.setHeight(50);
//        rectangle.setWidth(20);
//        rectangle.setFill(Color.YELLOW);
//        RotationAnimator slowRotationAnimator = new CanRotate(rectangle, 20);
//        RotationAnimator fastRotationAnimator = new CanRotate(rectangle, 2);
//        rectangle.setOnMouseClicked((e) -> {
//            System.out.println("(e.getButton() == MouseButton.SECONDARY) = " + (e.getButton() == MouseButton.SECONDARY));
//            if (e.getButton() == MouseButton.SECONDARY) {
//                fastRotationAnimator.rotateBy(90);
//            } else {
//                slowRotationAnimator.rotateBy(90);
//            }
//        });
//        stackPane.getChildren().add(rectangle);

        stage.setScene(new Scene(backgroundImage));
        stage.setHeight(271.5);
        stage.setWidth(400);
//        backgroundImage.setMaxHeight(271.5);
        stage.show();
    }

    public static StickHero getInstance() {
        return instance;
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