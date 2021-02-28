package org.example.SpringDemo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Computer {
    private MusicPlayer musicPlayer;

    @Autowired
    public Computer(MusicPlayer player) {
        this.musicPlayer = player;
    }

    public void play() {
        System.out.println("Comp" + musicPlayer.play());
    }
}
