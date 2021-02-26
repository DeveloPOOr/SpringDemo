package org.example.SpringDemo;

import java.util.List;

public class MusicPlayer {
    private List<Music> music;
    private String name;
    private int volume;

    public MusicPlayer(List<Music> music) {
        this.music = music;
    }

    public MusicPlayer(){}

    public List<Music> getMusic() {
        return music;
    }

    public void setMusic(List<Music> music) {
        this.music = music;
    }

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

    public void play() {
        music.forEach((m) -> System.out.println(m.getName()));
    }

    public void initMethod() {
        System.out.println("initialising");
    }

    public void destroyMethod() {
        System.out.println("Destroying");
    }
}
