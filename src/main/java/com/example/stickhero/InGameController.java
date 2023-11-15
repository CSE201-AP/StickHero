package com.example.stickhero;

import javafx.fxml.FXML;

public class InGameController {
    @FXML
    public void onPauseButtonClicked() {
        StickHero.getInstance().loadFXMLScene("fxml/pause-menu.fxml");
    }

    @FXML
    public void onOtherClick() {
        StickHero.getInstance().loadFXMLScene("fxml/game-over.fxml");
    }
}
