package com.example.stickhero;

import com.example.stickhero.environment.Background;
import com.example.stickhero.environment.BackgroundImage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
    private Button muteButton;
    @FXML
    private Button unmuteButton;
    private StickHero app;
    private boolean toggle = true;

    @FXML
    public void onPlayButtonClicked() {
        StickHero.getInstance().loadFXMLScene("fxml/in-game.fxml");
    }

    @FXML
    public void onSoundButtonClicked(ActionEvent event) {
//        Sounds.getSound();
        if (toggle){
            unmuteButton.setVisible(true);
            muteButton.setVisible(false);
            StickHero.getInstance().setMute(true);
        } else {
            muteButton.setVisible(true);
            unmuteButton.setVisible(false);
            StickHero.getInstance().setMute(false);
        }
        toggle ^= true;
    }

    public void initialize() {
        app = StickHero.getInstance();
        Progress progress = app.getProgress();
        previousScore.setText(String.valueOf(progress.getPastScore()));
        highScore.setText(String.valueOf(progress.getHighScore()));
        createBackground();
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
        AudioClip backgroundSound = Sound.getSound("bg_country");
        backgroundSound.setCycleCount(AudioClip.INDEFINITE);
        backgroundSound.play();
    }
}
