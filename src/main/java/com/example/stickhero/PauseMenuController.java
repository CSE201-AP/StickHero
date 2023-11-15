package com.example.stickhero;

import javafx.fxml.FXML;

public class PauseMenuController {
    @FXML
    public void onResumeButtonClick() {
        StickHero.getInstance().loadFXMLScene("fxml/in-game.fxml");
    }

    @FXML
    public void onSaveAndExitButtonClick() {
        System.exit(0);
    }
}
