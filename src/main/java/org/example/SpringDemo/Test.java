package org.example.SpringDemo;


import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );
//        MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
//        MusicPlayer musicPlayer2 = context.getBean("musicPlayer", MusicPlayer.class);

        Music music = context.getBean("classicalMusic", ClassicalMusic.class);
        System.out.println(music.getName());

        context.close();
    }
}
