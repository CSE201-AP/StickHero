package com.example.stickhero;

import javafx.fxml.FXML;

public class InGameController {
    @FXML
    public void onPauseButtonClicked() {
        StickHero.getInstance().loadFXMLScene("pause-menu.fxml");
    }

    @FXML
    public void onOtherClick() {
        StickHero.getInstance().loadFXMLScene("game-over.fxml");
    }
}
