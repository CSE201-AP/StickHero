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

//        AnchorPane anchorPane = new AnchorPane();
//
//        Background background = new Background();
//        for (int i=0; i<2; i++) {
//            BackgroundImage backgroundImage = new BackgroundImage(
//                    new Image(Objects.requireNonNull(getClass().getResourceAsStream("background" + (i+1) + ".png"))),
//                    (double) 10 /(i+1)
//            );
//            backgroundImage.minHeightProperty().bind(background.heightProperty());
//            background.getChildren().add(backgroundImage);
//        }
//
//        Foreground foreground = new Foreground(0.5);
//        AnchorPane.setBottomAnchor(foreground, 0D);
//        List<Building> buildings = new ArrayList<>();
//        for (int i=0; i<4; i++) {
//            Building building = new Building();
//            buildings.add(building);
//            foreground.getChildren().add(building);
//            building.setLayoutX(100 + i*100);
//        }
//
//        Hero hero = new Hero(new SpriteSheetInfo(
//                new Image(Objects.requireNonNull(getClass().getResourceAsStream("spritesheet.png"))),
//                new ArrayList<>(List.of(
//                        new Rectangle2D(0, 0,130,130),
//                        new Rectangle2D(130, 0,130,130),
//                        new Rectangle2D(0, 130,130,130),
//                        new Rectangle2D(130, 130,130,130)
//                )),
//                null,
//                2_00_000_000,
//                2_00_000_000
//        ));
//        hero.setFitWidth(35);
//        hero.setFitHeight(hero.getImage().getHeight()/hero.getImage().getWidth() * hero.getFitWidth());
//        hero.setLayoutY(-hero.getBoundsInLocal().getHeight());
//        hero.setLayoutX(100);
//
//        anchorPane.setOnMousePressed((e) -> {
////            if (e.getButton() == MouseButton.SECONDARY) {
////                hero.flip();
////                return;
////            }
//            System.out.println("hero.getMovementAnimator().getStatus() = " + hero.getMovementAnimator().getStatus());
//            if (hero.getMovementAnimator().getStatus() == Animation.Status.RUNNING) {
//                hero.flip();
//                return;
//            }
//            Stick stick = new Stick();
//            hero.setStick(stick);
//            stick.setLayoutX(hero.getBoundsInParent().getMaxX());
//            stick.startExtendStick();
//            stick.setFill(Color.YELLOW);
//            stick.setHandler((e2) -> {
//                EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent actionEvent) {
//                        foreground.panHorizontal(-stick.getScaleY());
//                        hero.getMovementAnimator().getAfterHandlers().remove(this);
//                    }
//                };
//                hero.getMovementAnimator().getAfterHandlers().add(handler);
//                hero.getMovementAnimator().moveBy(new Point2D(stick.getScaleY(), 0));
//            });
//            foreground.getChildren().add(stick);
//        });
//        anchorPane.setOnMouseReleased((e) -> {
//            Stick stick = hero.getStick();
//            if (stick != null && stick.getRotationAnimator().getStatus() != Animation.Status.STOPPED) {
//                stick.stopExtendStick();
//            }
//        });
//
//        foreground.getChildren().addAll(hero);
//
//        anchorPane.getChildren().addAll(background, foreground);
//        background.maxHeightProperty().bind(anchorPane.heightProperty());
//        anchorPane.maxHeightProperty().bind(stage.heightProperty());
//        anchorPane.minHeightProperty().bind(stage.heightProperty());
//        stage.setScene(new Scene(anchorPane));
//        stage.setWidth(335);
//        stage.setHeight(600);
//        stage.show();
//
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