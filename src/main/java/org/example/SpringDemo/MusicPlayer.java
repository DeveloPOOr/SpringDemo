package org.example.SpringDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class MusicPlayer {
    private Music music;
    private String name;
    private int volume;

    @Autowired
    public MusicPlayer(Music music) {
        this.music = music;
    }

    public MusicPlayer(){}



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String play() {
        return "Playing: " + music.getName();
    }

    public void initMethod() {
        System.out.println("initialising");
    }

    public void destroyMethod() {
        System.out.println("Destroying");
    }
}
