package com.example.stickhero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
        loadFXMLScene("fxml/main-menu.fxml");
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