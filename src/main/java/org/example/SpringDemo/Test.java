package org.example.SpringDemo;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class
        );

        MusicPlayer player = context.getBean("musicPlayer", MusicPlayer.class);
        System.out.println(player.getVolume());
        System.out.println(player.getName());
        player.play(Genres.RAP);

        context.close();
    }
}
