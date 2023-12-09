package com.example.stickhero;

import com.example.stickhero.environment.Background;
import com.example.stickhero.environment.BackgroundImage;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;

import java.util.Objects;

public class MainMenuController {
    @FXML
    public Background background;
    @FXML
    public Label previousScore;
    @FXML
    public Label highScore;
    @FXML
    public Button playButton;
    @FXML
    private Button muteButton;
    @FXML
    private Button unmuteButton;
    private StickHero app;
    private boolean toggle = true;

    private AudioClip backgroundSound = Sound.getSound("bg_country");

    @FXML
    public void onPlayButtonClicked() {
        Sound.getSound("button").play();
        StickHero.getInstance().loadFXMLScene("fxml/in-game.fxml");
    }

    @FXML
    public void onSoundButtonClicked(ActionEvent event) {
        Sound.getSound("button").play();
        if (toggle){
            unmuteButton.setVisible(true);
            muteButton.setVisible(false);
            StickHero.getInstance().setMute(true);
            backgroundSound.stop();
        } else {
            muteButton.setVisible(true);
            unmuteButton.setVisible(false);
            StickHero.getInstance().setMute(false);
            backgroundSound = Sound.getSound("bg_country");
            backgroundSound.setCycleCount(AudioClip.INDEFINITE);
            backgroundSound.setVolume(0.6);
            backgroundSound.play();
        }
        toggle ^= true;
    }

    public void initialize() {
        app = StickHero.getInstance();
        Progress progress = app.getProgress();
        previousScore.setText(String.valueOf(progress.getPastScore()));
        highScore.setText(String.valueOf(progress.getHighScore()));
        createBackground();

        if (app.getMute()){
            unmuteButton.setVisible(true);
            muteButton.setVisible(false);
            toggle = false;
        } else {
            muteButton.setVisible(true);
            unmuteButton.setVisible(false);
        }
    }

    private void createBackground() {
        for (int i=0; i<2; i++) {
            BackgroundImage backgroundImage = new BackgroundImage(
                    new Image(Objects.requireNonNull(getClass().getResourceAsStream("background" + (i+1) + ".png"))),
                    (double) 10 /(i+1)
            );
            backgroundImage.minHeightProperty().bind(background.heightProperty());
            background.getChildren().add(backgroundImage);
        }
        AnchorPane.setBottomAnchor(background, 0D);
        AnchorPane.setTopAnchor(background, 0D);
        if (!app.getMute()) {
            backgroundSound.setCycleCount(AudioClip.INDEFINITE);
            backgroundSound.setVolume(0.6);
            backgroundSound.play();
        }
    }

    @FXML
    public void onPlayButtonHover() {
        playButton.setOpacity(1);
    }
    @FXML
    public void onPlayButtonExit() {
        playButton.setOpacity(0.7);
    }

    @FXML
    public void onMuteButtonExit(MouseEvent mouseEvent) {
        muteButton.setOpacity(0.7);
        unmuteButton.setOpacity(0.7);
    }

    @FXML
    public void onMuteButtonHover(MouseEvent mouseEvent) {
        muteButton.setOpacity(1);
        unmuteButton.setOpacity(1);
    }
}
