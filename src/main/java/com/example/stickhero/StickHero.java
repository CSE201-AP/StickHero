package com.example.stickhero;

import com.example.stickhero.spritesheet.SpriteSheet;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
        loadFXMLScene("fxml/main-menu.fxml");

//        List<Rectangle2D> sprites = new ArrayList<Rectangle2D>(List.of(
//                new Rectangle2D(0, 0,130,130),
//                new Rectangle2D(130, 0,130,130),
//                new Rectangle2D(0, 130,130,130),
//                new Rectangle2D(130, 130,130,130)
//        ));
//        HBox hbox = new HBox();
//        SpriteSheet root = new SpriteSheet(
//                new Image(getClass().getResourceAsStream("example.png")),
//                sprites,
//                sprites,
//                2_00_000_000,
//                2_00_000_000
//        );
//        hbox.getChildren().add(root);
//        stage.setScene(new Scene(hbox));
//        root.animateMovement();
//        root.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                root.stopAnimations();
//            }
//        });
        stage.show();
        stage.setResizable(false);
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