package com.example.stickhero;

import com.example.stickhero.environment.BackgroundImage;
import com.example.stickhero.sprite.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
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
//        loadFXMLScene("fxml/main-menu.fxml");

//        Rectangle rect = new Rectangle();
//        rect.setWidth(10);
//        rect.setHeight(100);
//        rect.setFill(Color.BLUE);
//        MovementAnimator movementAnimator = new CanMove(
//                rect,
//                50,
//                () -> {},
//                (ActionEvent e) -> {
//                    System.out.println(rect.getTranslateX() + " " + rect.getTranslateY());
//                }
//        );

//        Rectangle rect2 = new Rectangle();
//        rect2.setHeight(100);
//        rect2.setWidth(10);
//        rect2.setFill(Color.RED);
//        MovementAnimator movementAnimator2 = new CanMove(
//                rect2,
//                50,
//                () -> {},
//                (ActionEvent e) -> {
//                    System.out.println(rect.getTranslateX() + " " + rect.getTranslateY());
//                }
//        );

//        List<Rectangle2D> sprites = new ArrayList<Rectangle2D>(List.of(
//                new Rectangle2D(0, 0,130,130),
//                new Rectangle2D(130, 0,130,130),
//                new Rectangle2D(0, 130,130,130),
//                new Rectangle2D(130, 130,130,130)
//        ));
//        HBox hbox = new HBox();
//        SpriteSheet root = new SpriteSheet(
//                new Image(getClass().getResourceAsStream("spritesheet.png")),
//                sprites,
//                sprites,
//                2_00_000_000,
//                2_00_000_000
//        );
//        hbox.getChildren().addAll(rect, rect2);
//        stage.setScene(new Scene(hbox));
//        root.animateMovement();
//        root.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                root.stopAnimations();
//            }
//        });
//        hbox.setAlignment(Pos.CENTER);
        Image image = new Image(getClass().getResourceAsStream("background.png"));
        BackgroundImage backgroundImage = new BackgroundImage(image, 4);
        backgroundImage.setMaxSize(400, 271.5);
        backgroundImage.setMinSize(400, 271.5);
        backgroundImage.setPrefSize(400, 271.5);
        stage.setScene(new Scene(backgroundImage));
        stage.show();
        stage.setHeight(271.5);
        stage.setWidth(400);
        stage.setResizable(false);
//        backgroundImage.setTranslateX(-500);
        backgroundImage.setOnMouseClicked(
                (e) -> {
                    System.out.println("Panning");
//                    backgroundImage.setTranslateX(backgroundImage.getTranslateX() - 100);
                    backgroundImage.panHorizontal(-700);
                }
        );
//        backgroundImage.panHorizontal(100);
//        scaleAnimator.scaleBy(0.2, 1);
//        scaleAnimator2.scaleTo(12, 200);
//        movementAnimator.moveBy(new Point2D(100, 100));
//        movementAnimator2.moveTo(new Point2D(rect2.getTranslateX() + 100, rect2.getTranslateY() + 100));
//        rotationAnimator.rotateBy(90, -rect.getWidth()/2, -rect.getHeight()/2);
//        rotationAnimator2.rotateTo(rect2.getRotate()+90, -rect2.getWidth()/2, -rect2.getHeight()/2);
//        rotationAnimator.rotateBy(90);
//        rotationAnimator2.rotateTo(rect2.getRotate()+90);
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