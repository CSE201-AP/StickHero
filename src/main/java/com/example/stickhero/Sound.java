package com.example.stickhero;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Sound {
    private final static Map<String, AudioClip> sounds = new HashMap<>();
    public static AudioClip getSound(String soundName){
        if (!sounds.containsKey(soundName)){
            String path = "sounds/" + soundName + ".mp3";
            Media sound = new Media(Objects.requireNonNull(Sound.class.getResource(path)).toString());
            AudioClip audioClip = new AudioClip(sound.getSource());
            sounds.put(soundName, audioClip);
        }
        return sounds.get(soundName);
    }
}
