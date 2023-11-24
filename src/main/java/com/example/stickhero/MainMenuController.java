package com.example.stickhero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController {
    @FXML
    private Button muteButton;
    @FXML
    private Button unmuteButton;

    private boolean toggle = true;

    @FXML
    public void onPlayButtonClicked() {
        StickHero.getInstance().loadFXMLScene("fxml/in-game.fxml");
    }

    @FXML
    public void onLoadButtonClicked() {
        StickHero.getInstance().loadFXMLScene("fxml/in-game.fxml");
    }

    @FXML
    public void onSoundButtonClicked(ActionEvent event) {
        if (toggle){
            unmuteButton.setVisible(true);
            muteButton.setVisible(false);
        } else {
            muteButton.setVisible(true);
            unmuteButton.setVisible(false);
        }
        toggle ^= true;
    }
}
