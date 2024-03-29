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
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.Objects;

public class StickHero extends Application {
    private static final double WIDTH = 335;
    private static final double HEIGHT = 600;
    private static StickHero instance;
    private Stage stage;
    private Hero hero;
    private static Foreground foreground;
    private Background background;
    private static Progress progress = new Progress();
    private static boolean mute = false;

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
    public void start(Stage stage) throws IOException {
        deserialize();
        stage.setOnCloseRequest((event) -> {
            try {
                if (hero != null){
                    progress.setCherries(hero.getCherries());
                    progress.setHighScore(Math.max(progress.getHighScore(), hero.getScore()));
                    progress.setPastScore(hero.getScore());
                }
                serialize();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        this.stage = stage;
        stage.setTitle("Stick Hero");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/Hero.png"))));
//        stage.setResizable(false);

        loadFXMLScene("fxml/main-menu.fxml");
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

    public static void deserialize() throws IOException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream("PastProgress.txt"));
            Object o = in.readObject();
            progress = (Progress) o;
        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
        } finally {
            if (in != null){
                in.close();
            }
        }
    }

    public static void serialize() throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("PastProgress.txt"));
            out.writeObject(progress);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (out != null){
                out.close();
            }
        }
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public boolean getMute(){
        return mute;
    }

    public void setMute(boolean mute){
        this.mute = mute;
    }
}