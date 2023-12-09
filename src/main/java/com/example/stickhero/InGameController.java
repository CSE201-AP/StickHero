package com.example.stickhero;

import com.example.stickhero.environment.Background;
import com.example.stickhero.environment.BackgroundImage;
import com.example.stickhero.environment.Foreground;
import com.example.stickhero.sprite.Building;
import com.example.stickhero.sprite.Hero;
import com.example.stickhero.sprite.ImageSprite;
import com.example.stickhero.sprite.Stick;
import com.example.stickhero.spritesheet.SpriteSheetGroup;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import java.util.*;

public class InGameController {
    private static final double STARTX = 60;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label cherriesLabel;
    @FXML
    private Background background;
    @FXML
    private Foreground foreground;
    @FXML
    private Label hint;
    private StickHero app;
    private Hero hero;
    private List<Building> buildings = new ArrayList<>();
    private static final Random random = new Random();
//    @FXML
//    private static final Double HEIGHT = Building.HEIGHT;


    public void initialize() {
        app = StickHero.getInstance();
        background = createBackground();
        hero = createHero();
        app.setHero(hero);
        createBuildings(2);
        hero.scoreProperty().addListener((observable, oldValue, newValue) -> {
            scoreLabel.setText(newValue.toString());
        });
        hero.cherriesProperty().addListener((observable, oldValue, newValue) -> {
            cherriesLabel.setText(newValue.toString());
        });
        foreground.getChildren().add(hero);
    }

    private double getLocalInForeground(double x) {
        return foreground.sceneToLocal(new Point2D(x, 0)).getX();
    }

    private void createBuildings(int n) {
        boolean initial = buildings.isEmpty();
        for (int i=0; i<n; i++) {
            Building building;
            if (buildings.isEmpty()) {
                building = new Building(STARTX);
                building.getChildren().remove(building.getPerfectBlock());
                building.setLayoutX(getLocalInForeground(0));
            } else {
                building = new Building();
                building.setLayoutX(getLocalInForeground(foreground.getPrefWidth()));
            }
            foreground.getChildren().add(building);
            if (!buildings.isEmpty()) {
                Building previousBuilding = buildings.get(buildings.size()-1);
                double maximumDistance = foreground.getPrefWidth()-building.getRectangle().getWidth()- STARTX;
                double distance = random.nextDouble(Building.MINIMUM_DISTANCE, maximumDistance);
                double position = previousBuilding.getBoundsInParent().getMaxX() + distance;
                if (initial) {
                    building.setLayoutX(position);
                } else {
                    building.getMovementAnimator().moveBy(new Point2D(foreground.localToScene(position, 0).getX()-foreground.getPrefWidth(), 0));
                }
            }
            buildings.add(building);
            hero.collisionCallbacks.put(building, () -> {
                if (hero.getScaleY() > 0) return;
                animateDeath();
                hero.collisionCallbacks.clear();
                hero.setDying(true);
            });
        }
    }

    private Background createBackground() {
        Background background = new Background();
        for (int i=0; i<2; i++) {
            BackgroundImage backgroundImage = new BackgroundImage(
                    new Image(Objects.requireNonNull(getClass().getResourceAsStream("background" + (i+1) + ".png"))),
                    (double) 10 /(i+1)
            );
            backgroundImage.minHeightProperty().bind(background.heightProperty());
            background.getChildren().add(backgroundImage);
        }
        return background;
    }

    private Hero createHero() {
        List<Rectangle2D> sprites = new ArrayList<>(List.of(
                new Rectangle2D(0, 0,130,130),
                new Rectangle2D(130, 0,130,130),
                new Rectangle2D(0, 130,130,130),
                new Rectangle2D(130, 130,130,130)
        ));
        Hero hero = new Hero(new Image(Objects.requireNonNull(getClass().getResourceAsStream("spritesheet.png"))),
                Map.of(
                     "default", new SpriteSheetGroup(sprites, 2_00_000_000, Hero.SPEED),
                     "movement", new SpriteSheetGroup(sprites, 2_00_000_000, Hero.SPEED),
                     "rotation", new SpriteSheetGroup(sprites, 50_000_000, Hero.SPEED)
                )
        );
        hero.setFitWidth(Hero.WIDTH);
        double height = hero.getImage().getHeight()/hero.getImage().getWidth() * Hero.WIDTH;
        hero.setFitHeight(height);
        hero.setTranslateX(-Hero.WIDTH);
        hero.setTranslateY(0);
        hero.setLayoutY(-height);
        hero.setLayoutX(STARTX);
        hero.getMovementAnimator().getAfterHandlers().add(onMovementFinishedEvent);
        return hero;
    }

    private void createCherry() {
        ImageSprite cherry = new ImageSprite(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/Cherry.png"))),
                Map.of(
                        "default", new SpriteSheetGroup(List.of(new Rectangle2D(0, 0, 79, 51)), 1, 0),
                        "idle", new SpriteSheetGroup(List.of(new Rectangle2D(0, 0, 79, 51)), 1, 0),
                        "pop", new SpriteSheetGroup(List.of(new Rectangle2D(0, 0, 79, 51)), 1, 0)
                )
        );
        cherry.setPreserveRatio(true);
        cherry.setFitWidth(Hero.WIDTH/2);
        double position = random.nextDouble(
                buildings.get(buildings.size()-2).getBoundsInParent().getMaxX(),
                buildings.get(buildings.size()-1).getBoundsInParent().getMinX()-cherry.getFitWidth()
        );
        foreground.getChildren().add(cherry);
        cherry.animate("idle");
        cherry.setLayoutX(position);
        cherry.setLayoutY(cherry.getFitHeight()+5);
        hero.collisionCallbacks.put(cherry, () -> {
            System.out.println("Collided with cherry");
            hero.collisionCallbacks.remove(cherry);
            cherry.animate("pop");
            foreground.getChildren().remove(cherry);
            hero.addCherry();
        });
    }

    private final EventHandler<ActionEvent> onMovementFinishedEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            Building lastBuilding = buildings.get(buildings.size()-1);
            hero.getStick().getRotationAnimator().getAfterHandlers().remove(onStickToppleEvent);
            if (hero.isDying()) {
                animateDeath();
            } else {
                createBuildings(1);
                double difference = lastBuilding.localToScene(lastBuilding.getBoundsInLocal()).getMaxX() - STARTX;
                foreground.panHorizontal(-difference);
                hero.increaseScore(1);
            }
        }
    };

    private void animateDeath() {
        if (hero.getScaleY() > 0) hero.flip();
        hero.getMovementAnimator().interrupt();
        hero.getMovementAnimator().getAfterHandlers().remove(onMovementFinishedEvent);
        hero.getMovementAnimator().setSpeedMs(Hero.FALL_SPEED);
        hero.getMovementAnimator().moveBy(new Point2D(0, Building.HEIGHT+hero.getFitHeight()));
    }


    private boolean scenePointAboveBuilding(double x, Building building) {
        Bounds buildingInScene = foreground.localToScene(building.getBoundsInParent());
        System.out.println(buildingInScene);
        System.out.println(x + " " + buildingInScene.getCenterY());
        return buildingInScene.contains(x, buildingInScene.getCenterY());
    }

    private final EventHandler<ActionEvent> onStickToppleEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            double stickTipX = foreground.localToScene(hero.getStick().getBoundsInParent()).getMaxX();
            Building lastBuilding = buildings.get(buildings.size()-1);
            boolean landed = scenePointAboveBuilding(stickTipX, lastBuilding);
            if (landed) {
                double difference = lastBuilding.localToScene(lastBuilding.getBoundsInLocal()).getMaxX() - STARTX;
                hero.getMovementAnimator().moveBy(new Point2D(difference, 0));
                background.panHorizontal(-difference);
                Bounds perfectBlockInScene = lastBuilding.localToScene(lastBuilding.getPerfectBlock().getBoundsInParent());
                if (perfectBlockInScene.contains(stickTipX, perfectBlockInScene.getCenterY())) {
                    hero.addCherry();
                    lastBuilding.getChildren().remove(lastBuilding.getPerfectBlock());
                }
                if (random.nextDouble() > 0) {
                    createCherry();
                }
            } else {
                hero.getMovementAnimator().moveBy(new Point2D(hero.getStick().getScaleY(), 0));
                hero.setDying(true);
            }
        }
    };

    @FXML
    private void onPauseButtonClicked() {
        StickHero.getInstance().loadFXMLScene("fxml/pause-menu.fxml");
    }

    @FXML
    private void onMousePressed(MouseEvent mouseEvent) {
        hint.setVisible(false);
        if (hero == null) return;
        if (hero.getMovementAnimator().getStatus() == Animation.Status.RUNNING) {
            double heroPosition  = foreground.localToScene(hero.getBoundsInParent()).getMaxX();
            boolean canFlip = !scenePointAboveBuilding(heroPosition-hero.getFitWidth(), buildings.get(buildings.size()-2))
                            && !(scenePointAboveBuilding(heroPosition, buildings.get(buildings.size()-1))
                                 && !hero.isDying()
                            );
            System.out.println("heroPosition = " + heroPosition);
            System.out.println("hero.getFitWidth() = " + hero.getFitWidth());
            System.out.println("scenePointAboveBuilding(heroPosition-hero.getFitWidth(), buildings.get(buildings.size()-2)) = " + scenePointAboveBuilding(heroPosition - hero.getFitWidth(), buildings.get(buildings.size() - 2)));
            System.out.println("scenePointAboveBuilding(heroPosition, buildings.get(buildings.size()-1)) = " + scenePointAboveBuilding(heroPosition, buildings.get(buildings.size() - 1)));
            System.out.println("hero.isDying() = " + hero.isDying());
            System.out.println("canFlip = " + canFlip);
            if (canFlip) hero.flip();
        } else {
            if (hero.isDying()) return;
            Stick stick = new Stick();
            hero.setStick(stick);
            foreground.getChildren().add(stick);
            stick.setLayoutX(buildings.get(buildings.size()-2).getBoundsInParent().getMaxX());
            stick.getRotationAnimator().getAfterHandlers().add(onStickToppleEvent);
            stick.startExtendStick();
        }
    }

    @FXML
    private void onMouseReleased(MouseEvent mouseEvent) {
        if (hero == null) return;
        if (hero.getStick() == null) return;
        if (hero.isDying()) return;
        if (hero.getMovementAnimator().getStatus() != Animation.Status.RUNNING) {
            hero.getStick().stopExtendStick();
        }
    }

    @FXML
    private void onMouseClicked(MouseEvent mouseEvent) {
    }

}
