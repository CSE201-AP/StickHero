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
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;

import java.io.IOException;
import java.util.*;

public class InGameController {
    private static final double STARTX = 60;
    private static final Random random = new Random();
    private static boolean revived = false;
    @FXML
    public AnchorPane pauseMenu;
    @FXML
    public Label highScore;
    @FXML
    public Label prevScore;
    @FXML
    public AnchorPane reviveOption;
    @FXML
    public Label cherriesCollected;
    @FXML
    private AnchorPane inGameScreen;
    @FXML
    private AnchorPane gameOver;
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
//    @FXML
//    private static final Double HEIGHT = Building.HEIGHT;
    private List<Building> buildings = new ArrayList<>();

    private final EventHandler<ActionEvent> onDeathEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            Progress progress = StickHero.getInstance().getProgress();
            progress.setPastScore(hero.getScore());
            progress.setHighScore(Math.max(hero.getScore(), progress.getHighScore()));
            progress.setCherries(hero.getCherries());
            inGameScreen.setVisible(false);
            gameOver.setVisible(true);
            prevScore.setText(String.valueOf(progress.getPastScore()));
            highScore.setText(String.valueOf(progress.getHighScore()));
            cherriesCollected.setText(String.valueOf(progress.getCherries()));
            if (Integer.parseInt(cherriesCollected.getText()) < 5){
                reviveOption.setVisible(false);
            }
        }
    };

    private final EventHandler<ActionEvent> onStickToppleEvent = new EventHandler<>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            System.out.println("Stick toppled");
            double stickTipX = foreground.localToScene(hero.getStick().getBoundsInParent()).getMaxX();
            Building lastBuilding = buildings.get(buildings.size() - 1);
            boolean landed = scenePointAboveBuilding(stickTipX, lastBuilding);
            if (landed) {
                System.out.println("Landed");
                double difference = lastBuilding.localToScene(lastBuilding.getBoundsInLocal()).getMaxX() - STARTX;
                hero.getMovementAnimator().moveBy(new Point2D(difference, 0));
                background.panHorizontal(-difference);
                System.out.println("Panned");
                Bounds perfectBlockInScene = lastBuilding.localToScene(lastBuilding.getPerfectBlock().getBoundsInParent());
                if (perfectBlockInScene.contains(stickTipX, perfectBlockInScene.getCenterY())) {
                    hero.increaseScore(1);
                    lastBuilding.getChildren().remove(lastBuilding.getPerfectBlock());
                }
//                if (random.nextDouble() > 0) {
//                    createCherry();
//                }
            } else {
                System.out.println("Didn't land");
                hero.getMovementAnimator().moveBy(new Point2D(hero.getStick().getScaleY() + hero.getFitWidth() / 2, 0));
                hero.setDying(true);
            }
        }
    };

    public void initialize() {
        app = StickHero.getInstance();
        reviveOption.setVisible(true);
        gameOver.setVisible(false);
        pauseMenu.setVisible(false);
        if (app.getHero() == null) {
            createBackground();
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
        Progress progress = app.getProgress();
        hero.setCherriesProperty(progress.getCherries());
        scoreLabel.setText(Integer.toString(hero.getScore()));
        cherriesLabel.setText(Integer.toString(hero.getCherries()));
    }

    private double getLocalInForeground(double x) {
        return foreground.sceneToLocal(new Point2D(x, 0)).getX();
    }

    private void createBuildings(int n) {
        boolean initial = buildings.isEmpty();
        for (int i = 0; i < n; i++) {
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
                Building previousBuilding = buildings.get(buildings.size() - 1);
                double maximumDistance = foreground.getPrefWidth() - building.getRectangle().getWidth() - STARTX;
                double distance = random.nextDouble(Building.MINIMUM_DISTANCE, maximumDistance);
                double position = previousBuilding.getBoundsInParent().getMaxX() + distance;
                if (initial) {
                    building.setLayoutX(position);
                } else {
                    building.getMovementAnimator().moveBy(new Point2D(foreground.localToScene(position, 0).getX() - foreground.getPrefWidth(), 0));
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

    private void createBackground() {
        for (int i = 0; i < 2; i++) {
            BackgroundImage backgroundImage = new BackgroundImage(
                    new Image(Objects.requireNonNull(getClass().getResourceAsStream("background" + (i + 1) + ".png"))),
                    (double) 10 / (i + 1)
            );
            backgroundImage.minHeightProperty().bind(background.heightProperty());
            background.getChildren().add(backgroundImage);
        }
        AnchorPane.setBottomAnchor(background, 0D);
        AnchorPane.setTopAnchor(background, 0D);
//        AudioClip backgroundSound = Sound.getSound("bg_country");
//        backgroundSound.setCycleCount(AudioClip.INDEFINITE);
//        backgroundSound.play();
    }

    private Hero createHero() {
        List<Rectangle2D> sprites = new ArrayList<>(List.of(
                new Rectangle2D(0, 0, 130, 130),
                new Rectangle2D(130, 0, 130, 130),
                new Rectangle2D(0, 130, 130, 130),
                new Rectangle2D(130, 130, 130, 130)
        ));
        Hero hero = new Hero(new Image(Objects.requireNonNull(getClass().getResourceAsStream("spritesheet.png"))),
                Map.of(
                        "default", new SpriteSheetGroup(sprites, 2_00_000_000, Hero.SPEED),
                        "movement", new SpriteSheetGroup(sprites, 2_00_000_000, Hero.SPEED),
                        "rotation", new SpriteSheetGroup(sprites, 50_000_000, Hero.SPEED)
                )
        );
        hero.setFitWidth(Hero.WIDTH);
        double height = hero.getImage().getHeight() / hero.getImage().getWidth() * Hero.WIDTH;
        hero.setFitHeight(height);
        hero.setTranslateX(-Hero.WIDTH);
        hero.setTranslateY(0);
        hero.setLayoutY(-height);
        hero.setLayoutX(STARTX);
        hero.getMovementAnimator().getAfterHandlers().add(onMovementFinishedEvent);
        return hero;
    }    private final EventHandler<ActionEvent> onMovementFinishedEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            System.out.println("Movement finished");
            Building lastBuilding = buildings.get(buildings.size() - 1);
            hero.getStick().getRotationAnimator().getAfterHandlers().remove(onStickToppleEvent);
            if (hero.isDying()) {
                animateDeath();
            } else {
                Sound.getSound("score").play();
                createBuildings(1);
                if (true || random.nextDouble(-1, 1) > 0) {
                    createCherry();
                }
                double difference = lastBuilding.localToScene(lastBuilding.getBoundsInLocal()).getMaxX() - STARTX;
                foreground.panHorizontal(-difference);
                // Destroy last perfect block
                lastBuilding.getChildren().remove(lastBuilding.getPerfectBlock());
                hero.increaseScore(1);
            }
//            hero.setStick(null);
        }
    };

    private void createCherry() {
        ImageSprite cherry = new ImageSprite(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/Cherry.png"))),
                Map.of(
                        "default", new SpriteSheetGroup(List.of(new Rectangle2D(0, 0, 79, 51)), 1, 0),
                        "idle", new SpriteSheetGroup(List.of(new Rectangle2D(0, 0, 79, 51)), 1, 0),
                        "pop", new SpriteSheetGroup(List.of(new Rectangle2D(0, 0, 79, 51)), 1, 0)
                )
        );
        cherry.setPreserveRatio(true);
        cherry.setFitWidth(Hero.WIDTH / 2);
        buildings.get(buildings.size() - 2).getRectangle().setFill(Color.RED);
        buildings.get(buildings.size() - 1).getRectangle().setFill(Color.GREEN);
        double position = random.nextDouble(
                buildings.get(buildings.size() - 2).getBoundsInParent().getMaxX(),
                buildings.get(buildings.size() - 1).getBoundsInParent().getMinX() - cherry.getFitWidth()
        );
//        System.out.println("buildings.get(buildings.size() - 1).getBoundsInParent().getMinX() = " + buildings.get(buildings.size() - 1).getBoundsInParent().getMinX());
//        System.out.println("(cherry.getFitWidth() + buildings.get(buildings.size() - 2).getBoundsInParent().getMaxX()) = " + (cherry.getFitWidth() + buildings.get(buildings.size() - 2).getBoundsInParent().getMaxX()));

        Rectangle rectangle = new Rectangle(
                buildings.get(buildings.size() - 1).getBoundsInParent().getMinX() - cherry.getFitWidth() - buildings.get(buildings.size() - 2).getBoundsInParent().getMaxX(),
                10
        );
        rectangle.setLayoutX(buildings.get(buildings.size() - 2).getBoundsInParent().getMaxX());
        foreground.getChildren().add(rectangle);
        foreground.getChildren().add(cherry);
        cherry.animate("idle");
        cherry.setLayoutX(position);
        cherry.setLayoutY(cherry.getFitHeight() + 5);
        hero.collisionCallbacks.put(cherry, () -> {
            Sound.getSound("eating_fruit").play();
            hero.collisionCallbacks.remove(cherry);
            cherry.animate("pop");
            foreground.getChildren().remove(cherry);
            hero.getMovementAnimator().getAfterHandlers().add(new EventHandler<>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    hero.getMovementAnimator().getAfterHandlers().remove(this);
                    foreground.getChildren().remove(cherry);
                }
            });
            hero.getMovementAnimator().getAfterHandlers().add(new EventHandler<>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (!hero.isDying()) hero.addCherry();
                    hero.getMovementAnimator().getAfterHandlers().remove(this);
                }
            });
        });
    }

    private void animateDeath() {
        hero.getMovementAnimator().interrupt();
        hero.getMovementAnimator().getAfterHandlers().remove(onMovementFinishedEvent);
        hero.getMovementAnimator().setSpeedMs(Hero.FALL_SPEED);
        hero.getMovementAnimator().moveBy(new Point2D(0, Building.HEIGHT + hero.getFitHeight()));
        Sound.getSound("dead").play();
        hero.getMovementAnimator().getAfterHandlers().add(onDeathEvent);
    }

    private boolean scenePointAboveBuilding(double x, Building building) {
        Bounds buildingInScene = foreground.localToScene(building.getBoundsInParent());
        return buildingInScene.contains(x, buildingInScene.getCenterY());
    }

    @FXML
    private void onPauseButtonClicked() {
        pauseMenu.setVisible(true);
        inGameScreen.setVisible(false);
        foreground.setVisible(false);
        if (foreground.getMovementAnimator().getStatus() == Animation.Status.RUNNING) {
            foreground.getMovementAnimator().interrupt();
        }
        for (Node node : foreground.getChildren()){
            if (node instanceof ImageSprite) {
                if (Animation.Status.RUNNING.equals(((ImageSprite) node).getMovementAnimator().getStatus())){
                    ((ImageSprite) node).getMovementAnimator().interrupt();
                }
                if (Animation.Status.RUNNING.equals(((ImageSprite) node).getRotationAnimator().getStatus())){
                    ((ImageSprite) node).getRotationAnimator().interrupt();
                }
            } else if (node instanceof Stick){
                if (Animation.Status.RUNNING.equals(((Stick) node).getRotationAnimator().getStatus())){
                    ((Stick) node).getRotationAnimator().interrupt();
                }
                if (Animation.Status.RUNNING.equals(((Stick) node).getScaleAnimator().getStatus())){
                    ((Stick) node).getScaleAnimator().interrupt();
                }
            }
        }
    }

    @FXML
    private void onMousePressed(MouseEvent mouseEvent) {
        if (!foreground.isVisible()){
            return;
        }
        hint.setVisible(false);
        if (hero == null) return;
        if (hero.getMovementAnimator().getStatus() == Animation.Status.RUNNING) {
            double heroPosition = foreground.localToScene(hero.getBoundsInParent()).getMaxX();
            boolean canFlip = !scenePointAboveBuilding(heroPosition - hero.getFitWidth(), buildings.get(buildings.size() - 2))
                    && !(scenePointAboveBuilding(heroPosition, buildings.get(buildings.size() - 1))
                    && !hero.isDying()
            );
            if (canFlip) hero.flip();
        } else {
            if (hero.isDying()) return;
            boolean idle = (hero.getStick() == null) || (hero.getStick() != null
                    && hero.getStick().getMovementAnimator().getStatus() != Animation.Status.RUNNING
                    && hero.getStick().getRotationAnimator().getStatus() != Animation.Status.RUNNING
                    && hero.getMovementAnimator().getStatus() != Animation.Status.RUNNING
                    && hero.getRotationAnimator().getStatus() != Animation.Status.RUNNING);
            if (!idle) return;
            Stick stick = new Stick();
            hero.setStick(stick);
            foreground.getChildren().add(stick);
            stick.setLayoutX(buildings.get(buildings.size() - 2).getBoundsInParent().getMaxX() - 2);
            stick.getRotationAnimator().getAfterHandlers().add(onStickToppleEvent);
            stick.startExtendStick();
        }
    }

    @FXML
    private void onMouseReleased(MouseEvent mouseEvent) {
        if (hero == null) return;
        if (hero.getStick() == null) return;
        if (hero.isDying()) return;
        if (hero.getMovementAnimator().getStatus() != Animation.Status.RUNNING && hero.getStick().getRotationAnimator().getStatus() != Animation.Status.RUNNING) {
            hero.getStick().stopExtendStick();
        }
    }

    @FXML
    private void onMouseClicked(MouseEvent mouseEvent) {
    }

    @FXML
    public void onHomeButtonClicked(ActionEvent actionEvent) {
        StickHero.getInstance().loadFXMLScene("fxml/main-menu.fxml");
    }

    @FXML
    public void onReviveButtonClicked(ActionEvent actionEvent) {
        revived = true;
        gameOver.setVisible(false);
        foreground.setVisible(true);
        inGameScreen.setVisible(true);
        hero.getMovementAnimator().setSpeedMs(Hero.SPEED);
//        System.out.println(foreground.getChildren().contains(hero));
        hero.getMovementAnimator().getAfterHandlers().remove(onDeathEvent);
        foreground.getChildren().remove(hero.getStick());
        hero.setTranslateX(0);
        hero.getTransforms().clear();
        hero.setTranslateY(0);
        if (hero.getScaleY() < 0) {
            hero.flip();
        }
        hero.setTranslateX(-hero.getFitWidth());
        hero.setLayoutX(getLocalInForeground(STARTX));
        hero.setLayoutY(-hero.getFitHeight());
        hero.getMovementAnimator().getAfterHandlers().add(onMovementFinishedEvent);
        hero.setStick(null);
        hero.setDying(false);
        reviveOption.setVisible(false);
    }

    @FXML
    public void onRestartButtonClicked(ActionEvent actionEvent) {
        app.setHero(null);
        revived = false;
        app.loadFXMLScene("fxml/in-game.fxml");
    }

    @FXML
    public void onResumeButtonClick(ActionEvent actionEvent) {
        pauseMenu.setVisible(false);
        inGameScreen.setVisible(true);
        foreground.setVisible(true);

        foreground.getMovementAnimator().resume();
        for (Node node : foreground.getChildren()){
            if (node instanceof ImageSprite) {
                if (Animation.Status.PAUSED.equals(((ImageSprite) node).getMovementAnimator().getStatus())){
                    ((ImageSprite) node).getMovementAnimator().resume();
                }
                if (Animation.Status.PAUSED.equals(((ImageSprite) node).getRotationAnimator().getStatus())){
                    ((ImageSprite) node).getRotationAnimator().resume();
                }
            } else if (node instanceof Stick){
                if (Animation.Status.PAUSED.equals(((Stick) node).getRotationAnimator().getStatus())){
                    ((Stick) node).getRotationAnimator().resume();
                }
                if (Animation.Status.PAUSED.equals(((Stick) node).getScaleAnimator().getStatus())){
                    ((Stick) node).getScaleAnimator().resume();
                }
            }
        }
    }

    @FXML
    public void onSaveAndExitButtonClick(ActionEvent actionEvent) throws IOException {
        Progress progress = StickHero.getInstance().getProgress();
        progress.setPastScore(hero.getScore());
        progress.setHighScore(Math.max(hero.getScore(), progress.getHighScore()));
        progress.setCherries(hero.getCherries());
        StickHero.serialize();
        System.exit(0);
    }
}
