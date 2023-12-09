package com.example.stickhero;

import java.io.Serializable;

public class Progress implements Serializable {
    private int cherries;
    private int pastScore;
    private int highScore;

    private static final long serialVersionUID = 1L;

    public int getCherries() {
        return cherries;
    }

    public void setCherries(int cherries) {
        this.cherries = cherries;
    }

    public int getPastScore() {
        return pastScore;
    }

    public void setPastScore(int pastScore) {
        this.pastScore = pastScore;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
