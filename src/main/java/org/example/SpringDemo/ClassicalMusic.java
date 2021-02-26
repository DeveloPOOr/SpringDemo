package org.example.SpringDemo;

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
