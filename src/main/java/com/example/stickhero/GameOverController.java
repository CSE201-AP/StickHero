package com.example.stickhero;

import javafx.fxml.FXML;

public class GameOverController {
    @FXML
    public void onReviveButtonClicked() {
        StickHero.getInstance().loadFXMLScene("in-game.fxml");
    }

    @FXML
    public void onHomeButtonClicked() {
        StickHero.getInstance().loadFXMLScene("main-menu.fxml");
    }

    @FXML
    public void onRestartButtonClicked() {
        StickHero.getInstance().loadFXMLScene("in-game.fxml");
    }
}
