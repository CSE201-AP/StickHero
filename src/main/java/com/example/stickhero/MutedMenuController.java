package com.example.stickhero;

import javafx.fxml.FXML;

public class MutedMenuController {
    @FXML
    public void onPlayButtonClicked() {
        StickHero.getInstance().loadFXMLScene("fxml/in-game.fxml");
    }

    @FXML
    public void onLoadButtonClicked() {
        StickHero.getInstance().loadFXMLScene("fxml/in-game.fxml");
    }

    @FXML
    public void onSoundButtonClicked() {
        StickHero.getInstance().loadFXMLScene("fxml/main-menu.fxml");
    }
}
