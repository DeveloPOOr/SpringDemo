package org.example.SpringDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class MusicPlayer {
    private Music classicalMusic;
    private Music rapMusic;

    @Value("${musicPlayer.name}")
    private String name;

    @Value("${musicPlayer.volume}")
    private int volume;

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }

    @Autowired
    public MusicPlayer(@Qualifier("classicalMusic") Music classicalmusic, @Qualifier("rap") Music rapMusic) {
        this.classicalMusic = classicalmusic;
        this.rapMusic = rapMusic;
    }

    public MusicPlayer(){}

    public void play(Genres genres) {
        List<String> songs = null;
        if(genres == Genres.CLASSICAL) {
            songs = classicalMusic.getName();
        }
        if(genres == Genres.RAP) {
            songs = rapMusic.getName();
        }
        int random = new Random().nextInt(songs.size());
        System.out.println(songs.get(random));
    }

    public void initMethod() {
        System.out.println("initialising");
    }

    public void destroyMethod() {
        System.out.println("Destroying");
    }
}
