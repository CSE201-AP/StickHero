package com.example.stickhero;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.transform.Rotate;
import javafx.animation.Transition.*;
import javafx.util.converter.IntegerStringConverter;

import java.util.ArrayList;

public class InGameController {
    @FXML
    private ImageView hero;
    @FXML
    private Rectangle stick, currBuilding, nextBuilding, currPerfectBlock, nextPerfectBLock;
    @FXML
    private Label score;

    @FXML
    public void onPauseButtonClicked() {
        StickHero.getInstance().loadFXMLScene("fxml/pause-menu.fxml");
    }

    private Timeline stickTimeline;
    private Timeline fallTimeline;

    @FXML
    public void onMousePressed() {
        startExtendStick();
//        stick.setHeight(stick.getHeight() + 1);
    }

    @FXML
    public void onMouseReleased() {
        stick.setDisable(true);
        stopExtendStick();
        fall();
    }

    private void startExtendStick() {
        stickTimeline = new Timeline(
            new KeyFrame(Duration.millis(8), event -> {
                if (stick.getHeight() < 500){
                    stick.setHeight(stick.getHeight() + 1);
                    stick.setLayoutY(stick.getLayoutY() - 1);
                }
            })
        );
        stickTimeline.setCycleCount(Timeline.INDEFINITE);
        stickTimeline.play();
    }

    private void stopExtendStick(){
        if (stickTimeline != null){
            stickTimeline.stop();
        }
    }

    private void fall(){
        if (stickTimeline == null){
            return;
        }
        Rotate rotate = new Rotate(0);
        rotate.setPivotX(stick.getWidth() / 2);
        rotate.setPivotY(stick.getHeight());
        stick.getTransforms().add(rotate);
        fallTimeline = new Timeline(
            new KeyFrame(Duration.millis((90 - rotate.getAngle()) / 25), event -> {
//                stick.setRotate(stick.getRotate() + 1);
                rotate.setAngle(rotate.getAngle() + 1);
            })
        );
        fallTimeline.setCycleCount(90);
        fallTimeline.play();
        fallTimeline.setOnFinished(event -> {
            moveHero();
        });
    }

    @FXML
    public void onMouseClicked() {
//        if (hero.getTransforms() == null) {
//            Rotate rotate = new Rotate(0);
//            rotate.setPivotX(hero.getFitWidth() / 2);
//            rotate.setPivotY(hero.getFitHeight());
//            hero.getTransforms().add(rotate);
//        } else {
//            Rotate rotate = (Rotate) hero.getTransforms().get(1);
//            if (rotate.getAngle() == 0) {
//                rotate.setAngle(180);
//            } else {
//                rotate.setAngle(0);
//            }
//        }
    }

    public void moveHero(){
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(8), event -> {
                hero.setLayoutX(hero.getLayoutX() + 1);
            })
        );
        if (checkBounds()){
            if (checkPerfect()){
                addScore();
            }
            timeline.setOnFinished(event -> {
                moveBackground();
            });
            timeline.setCycleCount((int) (nextBuilding.getLayoutX() - currBuilding.getLayoutX()));
        } else {
            timeline.setOnFinished(event -> {
                heroFall();
            });
            timeline.setCycleCount((int) (stick.getHeight() + 37));
        }
        timeline.play();
    }

    private void moveBackground() {
        addScore();
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(4), event -> {
                hero.setLayoutX(hero.getLayoutX() - 1);
                stick.setLayoutX(stick.getLayoutX() - 1);
                currBuilding.setLayoutX(currBuilding.getLayoutX() - 1);
                nextBuilding.setLayoutX(nextBuilding.getLayoutX() - 1);
                nextPerfectBLock.setLayoutX(nextPerfectBLock.getLayoutX() - 1);
            })
        );
        timeline.setCycleCount((int) (hero.getLayoutX() - 37));
        timeline.play();
    }

    private boolean checkBounds(){
        if (stick.getLayoutX() + stick.getHeight() < nextBuilding.getLayoutX()) return false;
        else return !(stick.getLayoutX() + stick.getHeight() > nextBuilding.getLayoutX() + nextBuilding.getWidth());
    }

    private boolean checkPerfect(){
        if (stick.getLayoutX() + stick.getHeight() < nextPerfectBLock.getLayoutX()) return false;
        else return !(stick.getLayoutX() + stick.getHeight() > nextPerfectBLock.getLayoutX() + nextPerfectBLock.getWidth());
    }

    private void heroFall(){
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(1), event -> {
                hero.setLayoutY(hero.getLayoutY() + 1);
            })
        );
        timeline.setCycleCount((int) (650 - hero.getLayoutY()));
        hero.setRotate(90);
        timeline.setOnFinished(event -> {
            StickHero.getInstance().loadFXMLScene("fxml/game-over.fxml");
        });
        timeline.play();
    }

    private void addScore(){
        score.setText(Integer.toString(Integer.parseInt(score.getText()) + 1));
    }
}
