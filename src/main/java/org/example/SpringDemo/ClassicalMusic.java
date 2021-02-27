package org.example.SpringDemo;

import org.springframework.stereotype.Component;

@Component
public class ClassicalMusic implements Music{

    @Override
    public String getName() {
        return "Bah";
    }

    private ClassicalMusic(){}

    public static ClassicalMusic create(){
        return new ClassicalMusic();
    }
}
