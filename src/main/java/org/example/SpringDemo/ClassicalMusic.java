package org.example.SpringDemo;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClassicalMusic implements Music{
    private List<String> songs = List.of("Полет Шмеля", "Рапсодия", "Соната");
    @Override
    public List<String> getName() {
        return songs;
    }

    private ClassicalMusic(){}

    public static ClassicalMusic create(){
        return new ClassicalMusic();
    }
}
